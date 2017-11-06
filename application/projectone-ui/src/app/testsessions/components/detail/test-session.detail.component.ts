import { Component, AfterContentInit } from '@angular/core';
import { SpinnerService } from '@pearson/angular-material';

@Component({
    selector: 'test-sessions-detail',
    templateUrl: './test-session.detail.component.html',
    styleUrls: [
        './test-session.detail.component.scss'
    ]
})
export class TestSessionDetailComponent implements AfterContentInit {

    constructor(private _spinnerService: SpinnerService) { }

    testSessions: any[];

    ngAfterContentInit(): void {
        this._spinnerService.register('testSessions.load');
        setTimeout(() => {
            this.testSessions = Array(20).fill(1).map(it => it + "map");
             this._spinnerService.resolve('testSessions.load');
        }, 3000);
    }
}