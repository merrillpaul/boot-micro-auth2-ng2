import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ExamineeComponent } from "./examinee.component";
import { ExamineeNewComponent } from "./examinee-new/examinee-new.component";


const routes: Routes = [
    {
        path: 'list',
        component: ExamineeComponent
    },

    {
        path: 'new',
        component: ExamineeNewComponent
    }, {
        path: '', redirectTo: 'list', pathMatch: 'full'
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class ExamineeRoutingModule {
}
