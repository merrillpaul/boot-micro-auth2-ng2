import { Component, OnInit } from '@angular/core';
import { fadeRouteAnimation } from '../app.animations';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']    ,
    animations: [fadeRouteAnimation],
    host: {'[@routeAnimation]': ''}
})
export class DashboardComponent implements OnInit {

    messages: string[] = ["Upcoming release on June 10", "BASC-3 SDS French release in September."]


    colorScheme: any = {
        domain: ['#1565C0', '#2196F3', '#81D4FA', '#FF9800', '#EF6C00'],
    };

    assessments = [
    {
        "name": "Assigned",
        "value": 4
    },
    {
        "name": "In Progress",
        "value": 6
    },
    {
        "name": "Needs Editing",
        "value": 2
    },
    {
        "name": "Ready",
        "value": 7
    },
    {
        "name": "Report Complete",
        "value": 3
    }
    ];

    constructor() {
    }

    ngOnInit() {
    }

}
