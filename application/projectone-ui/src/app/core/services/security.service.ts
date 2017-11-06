import { Injectable } from "@angular/core";
import { RequestOptions } from "@angular/http";
import { Router } from "@angular/router";

import { Observable } from "rxjs/Observable";
import { Subject } from "rxjs/Subject";

import { User } from "../dto/user/user";
import { MeResponse } from '../dto/user/me.response';
import { LoginRequest } from "../dto/login/login.request";
import { LoginResponse } from "../dto/login/login.response";
import { OAuthService } from "../oauth/oauth.service";
import { OAuthResponse } from "../oauth/oauth-response";

import { HttpService } from "../../shared/http/http.service";
import { environment } from "../../../environments/environment";
import { TokenService } from "../token/token.service";

@Injectable()
export class SecurityService {

    private _currentUser: Subject<User>;
    validUser: boolean = true;
    loginResponse = '';
    time: number;
    sessionValid: boolean;
    private _lastUser: User;


    constructor(private http: HttpService,
        private oauthService: OAuthService,
        private tokenService: TokenService,       
        private router: Router) {
        this._currentUser = new Subject<User>();
        this._currentUser.subscribe(data => {
            if(data && data.loggedIn === true) {
                this._lastUser = data;
            }
        })
    }

    public get currentUser(): Observable<User> {
        return this._currentUser.asObservable().share();
    }

    authenticate(user: LoginRequest): Observable<any> {
        let response = new Subject<LoginResponse>();
        this.oauthService.oauthLogin(user.username, user.password).subscribe(data => {
            this.saveResponse(data);
            let oauthRes: string = JSON.stringify(data);
            this.fetchUserProfile().subscribe((data: MeResponse) => {
                this.setCurrentUser(data);
                response.next({ status: true });
                response.complete();
            }, errorCode => {
                response.next({ status: false, errorCode: errorCode });
                response.complete();
            });


        }, errorCode => {
            response.next({ status: false, errorCode: errorCode });
            response.complete();
        });

        return response.asObservable();
    }

    private setCurrentUser(data: MeResponse) {
        let currentUser = User.createUser(data);
        let profileName = data.firstName;
        this.saveProfileName(profileName);
        this._currentUser.next(currentUser);
    }

    saveResponse(data: OAuthResponse) {
        this.tokenService.saveTokenData(data);
    }

    fetchUserProfile(): Observable<any> {
        return this.http.get(this.prepareProfileUrl());
    }

    refreshUserProfile(): Observable<boolean> {
        let result = new Subject<boolean>();
        if (this._lastUser) {
             this.setCurrentUser(this._lastUser);
             setTimeout(() => {
             result.next(true);
             result.complete();
             }, 1);
             return result
        }
        this.fetchUserProfile().subscribe((data: MeResponse) => {
                this.setCurrentUser(data);
                result.next(true);
                result.complete();
            }, errorCode => {
                result.next(false);
                result.complete();
            });
        return result.asObservable();
    }

    prepareProfileUrl(): string {
        return environment.oauthServerBaseUrl + "/me";
    }

    saveProfileName(name: string) {
        this.tokenService.setProfileName(name);
    }

    clearUser(): void {
         this.tokenService.clearTokenData();   
         this._currentUser.next(new User());
         this._lastUser = null;
    }

    logout(): Observable<Response> {
        
        let logoutResponse = new Subject<any>();
        this.http.get(this.prepareLogoutUrl(), new RequestOptions({ headers: null })).subscribe((response) => {   
            this.clearUser();    
            logoutResponse.next(response);
            
        }, err => {
            // ui logout anyway
            this.clearUser();             
            logoutResponse.error(err);           
        });
        
        return logoutResponse.asObservable();

    }


    prepareLogoutUrl() {
        return environment.oauthServerBaseUrl + "/api/logout";
    }
}
