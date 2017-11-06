import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../shared/shared.module';
import { MyTestSessionsRoutingModule } from './my-test-sessions.routing.module';

import { MyTestSessionsHomeComponent } from './components/test-sessions.home.component';
import { TestSessionDetailComponent } from './components/detail/test-session.detail.component';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        MyTestSessionsRoutingModule
    ],
    exports: [],
    declarations: [
        MyTestSessionsHomeComponent,
        TestSessionDetailComponent
    ],
    providers: [],
})
export class MyTestSessionsModule { }
