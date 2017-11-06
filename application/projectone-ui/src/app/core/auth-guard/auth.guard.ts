import { Router, CanActivate } from "@angular/router";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";

import { NavigationEmitter } from "../navigation.emitter";
import { TokenService } from "../token/token.service";
import { CoreEventService } from "../services/core.event.service";
import { SecurityService } from "../services/security.service";

@Injectable()
export class AuthenticationGuard implements CanActivate {
    constructor(private router: Router, private tokenService: TokenService,
                private _eventService: CoreEventService,
                private _securityService: SecurityService,
                private navEmitter: NavigationEmitter) {
    }

    public canActivate(): boolean | Observable<boolean> {
        if (this.tokenService.hasValidToken()) {
            return this._securityService.refreshUserProfile();            
        } else {
            this._eventService.fireTimedout();           
        }
        return false;
    }

    public routeHome() {
        this.navEmitter.showNav.emit(true);        
        this.router.navigate(['examinee']);
    }
}
