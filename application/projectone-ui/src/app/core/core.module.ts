import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecurityService } from './services/security.service';
import { CoreEventService } from './services/core.event.service';
import { OAuthService } from './oauth/oauth.service';

import { CoreLocalStorageService } from './storage/core-local-storage.service';
import { CoreSessionStorageService } from './storage/core-session-storage.service';
import { AuthenticationGuard } from './auth-guard/auth.guard';
import { NavigationEmitter } from './navigation.emitter';
import { TokenService } from './token/token.service';
import { SharedModule } from '../shared/shared.module';

export { User } from './dto/user/user';
export { MeResponse } from './dto/user/me.response';
export { LoginRequest } from './dto/login/login.request';
export { LoginResponse } from './dto/login/login.response';
export { TokenService } from './token/token.service';
export { CoreEventService } from './services/core.event.service';
export { SecurityService } from './services/security.service';
export { OAuthService } from './oauth/oauth.service';
export { Util } from './util/util';
export { AuthenticationGuard } from './auth-guard/auth.guard'
export { Logger } from './logger/logging.service';
export { BaseDTO } from './dto/base.dto';

@NgModule({
    imports: [
        CommonModule,
        SharedModule        
    ],
    declarations: [],
    exports: [],
    providers: [
        SecurityService,
        OAuthService,
        CoreLocalStorageService,
        CoreSessionStorageService,
        AuthenticationGuard,
        NavigationEmitter,
        TokenService,
        CoreEventService
    ]
})
/**
 * CoreModule contains single-use classes. Multi-uses classess
 * should go in SharedModule.
 */
export class CoreModule {
    constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
        if (parentModule) {
            throw new Error('CoreModule is already loaded. Import it AppModule only.');
        }
    }

    static forRoot(): ModuleWithProviders {
        return {
            ngModule: CoreModule
        };
    }
}
