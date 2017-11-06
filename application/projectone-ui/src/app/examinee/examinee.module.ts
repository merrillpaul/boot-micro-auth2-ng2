import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';

import { SharedModule } from "../shared/shared.module";
import { ExamineeRoutingModule } from "./examinee-routing.module";
import { ExamineeComponent } from "./examinee.component";
import { ExamineeService } from "./services/examinee.service";
import { ExamineeNewComponent } from "./examinee-new/examinee-new.component";
import { ExamineeDetailsComponent } from "./examinee-details/examinee-details.component";


export { ExamineeService } from "./services/examinee.service";
export { Examinee } from './dto/examinee'
export { Gender } from './dto/gender'


@NgModule({
    imports: [SharedModule, ExamineeRoutingModule, CommonModule],
    declarations: [ExamineeComponent, ExamineeNewComponent, ExamineeDetailsComponent],
    providers: [ExamineeService]

})
export class ExamineeModule {
}
