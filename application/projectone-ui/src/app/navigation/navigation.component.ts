import { Component } from "@angular/core";
import { DomSanitizer } from '@angular/platform-browser';
import { Router, NavigationEnd, NavigationStart } from '@angular/router';

import { SpinnerService } from '@pearson/angular-material';
import { Observable } from "rxjs/Observable";

import { SecurityService, TokenService, User } from '../core/core.module';

@Component({
    selector: 'app-nav',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.scss']
})


export class NavigationComponent {
    private title: string = "ProjectOne";
    private profileName: string;
    private hidden: boolean = true;
    currentUser: User;
    hideFooter: boolean = true;
    private _drawerRoutes: any[] = [];


    constructor(private tokenService: TokenService, private _securityService: SecurityService,
        private _spinnerService: SpinnerService,
        private router: Router) {
        this.hidden = this.tokenService.hasValidToken();
        this.tokenService.tokenValidityEmitter.subscribe(event => {
            this.hidden = event;
        });
        this._securityService.currentUser.subscribe((user: User) => { 
            this.currentUser = user; 
            if (this.currentUser && this.currentUser.loggedIn === true) {
                this._drawerRoutes = [
                    {
                        route: 'home',
                        icon: 'local_library',
                        label: 'Dashboard'
                    },{
                        route: 'examinee',
                        icon: 'local_library',
                        label: 'Examinees'
                    },{
                        route: 'testsessions',
                        icon: 'local_library',
                        label: 'My Test Sessions'
                    },{
                        route: 'build',
                        icon: 'local_library',
                        label: 'Build'
                    }
                ];
            } else {
                this._drawerRoutes = [
                    {
                        route: 'home',
                        icon: 'local_library'
                    }
                ];
            }
        });
        this.router.events.subscribe((val) => this.onRouterChange(val));       
    }

    public get navDrawerList(): any[] {
       return this._drawerRoutes;
    }

    getProfileName() {
        return this.tokenService.getProfileName();
    }

    hide() {
        return !this.hidden;
    }

    logout() {  
        this._spinnerService.register();      
        this._securityService.logout().subscribe(
            () => {
                this.router.navigate(['/']);
                setTimeout(() => this._spinnerService.resolve(), 1500);                
            },
            (err) => {
                 this.router.navigate(['/']);
                 this._spinnerService.resolve();
            }
        );
    }
  
    private onRouterChange(val): void {     
        if (val instanceof NavigationEnd) {
            this.hideFooter = false;
            return;
        } 

        if ( val instanceof NavigationStart) {
            this.hideFooter = true;
            return;
        }
    }
}
