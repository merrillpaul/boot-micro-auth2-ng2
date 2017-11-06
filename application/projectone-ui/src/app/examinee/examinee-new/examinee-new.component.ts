import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Location } from '@angular/common';

import { Examinee } from "../dto/examinee";
import { ExamineeService } from "../services/examinee.service";
import { Gender } from "../dto/gender";
import { Observable } from "rxjs/Observable";
@Component({
    selector: 'new-examinee',
    templateUrl: './examinee-new.component.html',
    styleUrls: ['./examinee-new.component.scss']
})

export class ExamineeNewComponent implements OnInit {
    title: string = "New Examinee"
    subTitle: string = "Create new Examinee"
    examinee: Examinee;
    genderValues: string[];
    genderType: typeof Gender;
    updateRequest: boolean = false;

    constructor(private examineeService: ExamineeService, private router: Router, public location: Location) {

        this.examinee = this.examineeService.getCurrentExaminee();
        if (this.examinee && !!this.examinee.id) {
            this.updateRequest = true;
        } else {
            this.examinee = new Examinee();
        }
    }


    ngOnInit() {
        this.genderValues = Object.keys(Gender);
        this.genderValues = this.genderValues.slice(this.genderValues.length / 2);
    }

    submit() {
        this.createOrUpdateExaminee().subscribe(response => {
            this.router.navigate(['/examinee']);
        })
    }

    createOrUpdateExaminee(): Observable<any> {
        if (this.updateRequest) {
            return this.examineeService.updateExaminee(this.examinee);
        } else {
            return this.examineeService.createExaminee(this.examinee);
        }
    }
}
