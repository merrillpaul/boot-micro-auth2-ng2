import { NgModule } from '@angular/core';
import { SharedModule } from "../shared/shared.module";
import { DashboardService } from './dashboard.service';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';


@NgModule({
    imports: [SharedModule, DashboardRoutingModule],
    declarations: [DashboardComponent],
    providers: [DashboardService],
    exports: [DashboardComponent]
})

export class DashboardModule {

}
