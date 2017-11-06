import { Component, Optional, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ExamineeAssessmentSession } from '../dto/examinee-assessment';
import { InstrumentAssessment } from '../dto/instrument-assessment';
import { Examinee } from '../../examinee/examinee.module'
import { fadeRouteAnimation } from '../../app.animations';

import { FadeOnEnterAnimation } from '@pearson/angular-material';

@Component({
    selector: 'build-examinee-assessment',
    templateUrl: './build-assessment.component.html',
    styleUrls: [
        './build-assessment.component.scss'
    ],
    animations: [
        FadeOnEnterAnimation(1000),
        fadeRouteAnimation
    ],
     host: {'[@routeAnimation]': ''}
})
export class BuildExamineeAssessmentComponent implements OnInit {
    step1Active: boolean = true;
    step2Active: boolean = false;
    step1Completed: boolean = false;
    step2Completed: boolean = false;
    _examineeAssessment: ExamineeAssessmentSession = {
        instrumentAssessments: [],
        valid : false
    };
    savedMessage: string = '';
    savedExaminee: boolean = false;

    constructor(@Optional() private route: ActivatedRoute) {       
    }

    ngOnInit() {
        this._examineeAssessment.examinee = new Examinee();
        let examineeFromRoute = this.route.snapshot.data.examinee;
        if (examineeFromRoute) {
            this._examineeAssessment.examinee = examineeFromRoute;
            this._markFirstStep();
        }
    }

    onExamineeSaved(examinee: Examinee) {
        this.savedMessage = `Saved ${examinee.firstName} examinee`;
        this._markFirstStep();
        this.savedExaminee = true;
        setTimeout(() => this.savedExaminee = false, 3000);
    }

     editInlineExaminee(): void {
        this.savedExaminee = false;
        this.savedMessage = '';
        this.step1Completed = false;
        this.step1Active = true;
        this.step2Active = false;
    }

    private _markFirstStep() : void {
        this.step1Completed = true;        
        this.step2Active = true;
        this.step1Active = false;
    }


   public get examineeAssessment(): ExamineeAssessmentSession {
       return this._examineeAssessment;
   }

}