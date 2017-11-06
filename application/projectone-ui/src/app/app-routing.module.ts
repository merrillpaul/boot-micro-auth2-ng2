import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { CoreModule, AuthenticationGuard } from './core/core.module';
const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'logout', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', loadChildren: 'app/login/login.module#LoginModule' },
    { path: 'home', loadChildren: 'app/dashboard/dashboard.module#DashboardModule', canActivate: [AuthenticationGuard]},
    { path: 'examinee', loadChildren: 'app/examinee/examinee.module#ExamineeModule', canActivate: [AuthenticationGuard]},
    { path: 'build', loadChildren: 'app/examineeAssessment/examinee-assessment.module#ExamineeAssessmentModule', canActivate: [AuthenticationGuard]},
    { path: 'testsessions', loadChildren: 'app/testsessions/my-test-sessions.module#MyTestSessionsModule', canActivate: [AuthenticationGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true }), CoreModule],
    exports: [RouterModule]
})
export class AppRoutingModule {
}