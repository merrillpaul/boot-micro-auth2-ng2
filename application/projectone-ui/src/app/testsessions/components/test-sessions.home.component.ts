import { Component } from '@angular/core';
import { fadeRouteAnimation } from '../../app.animations';

import { MediaQueryService } from '@pearson/angular-material';

@Component({
    selector: 'mytest-sessions-home',
    templateUrl: './test-sessions.home.component.html',
    styleUrls: [
        './test-sessions.home.component.scss'
    ],
    animations: [fadeRouteAnimation],
    host: {'[@routeAnimation]': ''} 
})
export class MyTestSessionsHomeComponent {

    constructor(
        public media: MediaQueryService
    ) { }   

    
    testSessionRoutes: any[] = [

    ];
}