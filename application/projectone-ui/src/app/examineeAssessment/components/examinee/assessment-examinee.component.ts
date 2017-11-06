import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Response, URLSearchParams } from "@angular/http";
import { Observable } from "rxjs/Observable";

import { Examinee, Gender, ExamineeService } from '../../../examinee/examinee.module'
import { BaseDTO } from '../../../core/core.module'

@Component({
    selector: 'assessment-examinee',
    templateUrl: './assessment-examinee.component.html',
    styleUrls: ['./assessment-examinee.component.scss']
})
export class AssessmentExamineeComponent {

    genderValues: string[];
    genderType: typeof Gender;
    

    constructor(
        private _examineeService: ExamineeService
    ) { }

    @Input('examinee')
    examinee: Examinee;

    @Output('examineeSaved')
    savedExaminee: EventEmitter<Examinee> = new EventEmitter<Examinee>();

    ngOnInit() {
        this.genderValues = Object.keys(Gender);
        this.genderValues = this.genderValues.slice(this.genderValues.length / 2);
        
    }


    createExamineeAndMoveOn(): void {

        if (this.examinee.id) {
            this._examineeService.updateExaminee(this.examinee).subscribe((data: BaseDTO) => {    
                this.examinee.version = data.version;
                this.savedExaminee.emit(this.examinee);
            });
        } else {
            this._examineeService.createExaminee(this.examinee).subscribe((data: BaseDTO) => {    
                this.examinee.id = data.id;
                this.examinee.version = data.version;
                this.savedExaminee.emit(this.examinee);
            });
        }

    }

}