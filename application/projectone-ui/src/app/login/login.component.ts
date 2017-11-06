import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { XSRFStrategy, CookieXSRFStrategy } from "@angular/http";
import { MediaQueryService, SpinnerService } from '@pearson/angular-material';

import { User, LoginRequest, LoginResponse, Util } from "../core/core.module";
import { SecurityService, TokenService, OAuthService } from "../core/core.module";
import { fadeRouteAnimation } from '../app.animations';

@Component({
    selector: 'login',
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.scss"],
    providers: [
        { provide: XSRFStrategy, useFactory: Util.xsrfFactory }
    ]  ,
    animations: [fadeRouteAnimation],
    host: {'[@routeAnimation]': ''} 
})

export class LoginComponent implements OnInit {

    user: User = new User();
    unknownUser: LoginRequest = {};
    validLogin: boolean = true;

    validUser: boolean = true;
    welcome = 'Login Page';
    welcomeNote = this.welcome;
    greeting: string;
    loginResponse = '';
    classMap: any;
    time: number;
    sessionValid: boolean;


    constructor(private router: Router, private oauthService: OAuthService
        , private tokenService: TokenService,
        private _spinnerService: SpinnerService,
        private _securityService: SecurityService, private media: MediaQueryService) {
        // User might already have valid token
        this.reRouteIfNeeded();

        this.classMap = { 'alert': false, 'alert-success': false, 'alert-danger': false };

    }

    authenticate() {
        this._spinnerService.register();

        this._securityService.authenticate(this.unknownUser).subscribe((response: LoginResponse) => {
            if (response.status === true) {                
                this.isValidLogin(true)
                // just to simulate the loading
                setTimeout(() => { 
                    this._spinnerService.resolve(); 
                    this.routeToHome();
                }, 1000);
            } else {
                let errorCode = response.errorCode;
                switch (errorCode / 100) {
                    case 4:
                        this.invalidCredentials();
                        break;
                    case 5:
                        this.internalServerError();
                        break;
                    default:
                        this.unknownError();

                }
                this._spinnerService.resolve();
                this.isValidLogin(false);
            }
        });

    }

    private invalidCredentials() {
        this.classMap = { 'alert': true, 'alert-success': false, 'alert-danger': true };
        this.loginResponse = 'Oops, Your credentials did not macth!';
        this.validUser = false;
        return;
    }

    private internalServerError() {
        this.classMap = { 'alert': true, 'alert-success': false, 'alert-danger': true };
        this.loginResponse = 'Oops, Authentication server error occured!';
        this.validUser = false;
        return;
    }

    private unknownError() {
        this.classMap = { 'alert': true, 'alert-success': false, 'alert-danger': true };
        this.loginResponse = 'Oops, Authentication Server seems to be down!';
        this.validUser = false;
        return;
    }

    ngOnInit() {
        // if (this.authenticationGuard.canActivate()) {
        //   this.authenticationGuard.routeHome();
        // }
    }


    private routeToHome() {
        this.router.navigate(['/home']);
    }

    private isValidLogin(state: boolean) {
        this.validLogin = state;
    }

   

    /**
     * Checks if user has a valid token. If yes then re-routes to home.
     */
    reRouteIfNeeded() {
        if (this.tokenService.hasValidToken()) {
            this.router.navigate(['/home'])
        }
    }

}
