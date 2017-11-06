import { Component, AfterViewInit, ViewContainerRef } from "@angular/core";
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { SpinnerService, ToastService, MediaQueryService, ToastDismissMode, ToastRequest } from '@pearson/angular-material';
import { CoreEventService, SecurityService } from './core/core.module';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {

    constructor(private _vcr: ViewContainerRef,
        private _eventService: CoreEventService,
        private _securityService: SecurityService,
        private _toastService: ToastService,
        private _router: Router,
        private _translate: TranslateService) {
        this._translate.setDefaultLang("en");
    }

    ngAfterViewInit(): void {
        this._toastService.setup(this._vcr);
        this._eventService.timedout.subscribe(
            () => {
                this._router.navigate(['logout']);
                this._securityService.clearUser();
                this._toastService.warning(new ToastRequest('Timedout!', 'Your previous session was timed out. Please login again'));
            }
        );
    }
    title = 'ProjectOne!';
}
