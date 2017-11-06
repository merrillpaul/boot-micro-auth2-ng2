import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';

import { SharedModule } from "../shared/shared.module";
import { LoginRoutingModule } from "./login-routing.module";
import { LoginComponent } from "./login.component";
import { OAuthService } from "../core/oauth/oauth.service";


@NgModule({
    imports: [
        CommonModule, SharedModule, LoginRoutingModule
    ],
    declarations: [
        LoginComponent
    ]
})

export class LoginModule {
}
