import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { HttpModule, XSRFStrategy } from "@angular/http";
import { Util } from "../core/util/util";
import { HttpService } from "./http/http.service";
import { CustomDatePipe } from "./pipes/date.pipe";
import { PearsonMaterialModule } from '@pearson/angular-material';

import { TranslateModule } from '@ngx-translate/core';

export { HttpService };
export { TranslateService } from '@ngx-translate/core';

/**
 * This SharedModule contain all common classes that are sharable with
 * other modules.
 */
@NgModule({
    imports: [CommonModule, HttpModule, PearsonMaterialModule, FormsModule],
    exports: [FormsModule, HttpModule, CustomDatePipe, PearsonMaterialModule, TranslateModule],
    declarations: [CustomDatePipe],
    providers: [
        HttpService,
        { provide: XSRFStrategy, useFactory: Util.xsrfFactory }
    ],
})

export class SharedModule {
}
