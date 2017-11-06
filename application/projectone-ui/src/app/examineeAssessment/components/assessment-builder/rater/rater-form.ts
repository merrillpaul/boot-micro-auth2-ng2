import { Component, Input, forwardRef, Inject, ViewChild, AfterViewInit, AfterContentInit, Output, EventEmitter } from '@angular/core';
import { MdButtonToggleChange } from '@angular/material'
import { FadeOnEnterAnimation } from '@pearson/angular-material';
import { NgForm } from '@angular/forms';

import { TranslateService } from '@ngx-translate/core';
import { Rater } from '../../../dto/rater';
import { AssessmentRaterBuilderComponent } from './rater-builder';

@Component({
    selector: 'rater-form',
    templateUrl: './rater-form.html',
    styleUrls: [
        './rater-form.scss'
    ],
    animations: [
        FadeOnEnterAnimation(1000)
    ],
})
export class AssessmentRaterFormComponent implements AfterViewInit, AfterContentInit  {
    
    public deliveryMethods: any[];
    constructor(
        private _translateService: TranslateService,
        @Inject(forwardRef(() => AssessmentRaterBuilderComponent)) private _parent: AssessmentRaterBuilderComponent
    ) { 
        
        /**
         * FORM_DELIVERY_TYPE.Manual.Entry", - Paper
"FORM_DELIVERY_TYPE.On.Screen.Administration", - Administer on Screen
"FORM_DELIVERY_TYPE.Remote.On.Screen.Administration" - Email link
         */
        debugger;
        this.deliveryMethods = this._parent.formDeliveryTypes.map (it => {
            return {
                value: it,
                label: this._translateService.get(`examineeAssessment.raterForm.${it}`)
            }
        });
    }

    @ViewChild('raterForm') private _raterForm: NgForm;

    @Output('validityChange')
    validityChange: EventEmitter<Rater> = new EventEmitter<Rater>();

    ngAfterViewInit(): void {

       this._raterForm.valueChanges
            .debounceTime(300)
            .distinctUntilChanged()
            .subscribe(() => {
                if ( this._raterForm && this._raterForm.form) {
                    this._rater.valid = this._raterForm.form.valid;
                    this.validityChange.emit(this._rater);
                }
            });
    }

    ngAfterContentInit(): void {
        if (!this.rater.deliveryType && this.deliveryMethods.length > 0) {
            // setting the first one
            this.rater.deliveryType = this.deliveryMethods[0].value;
        }
    }


    private _rater: Rater;

    @Input() index: number;    

    @Input()
    editing: boolean;

    @Input('rater')
    public set rater(rater: Rater) {
        this._rater = rater;
    }

    public get rater(): Rater {
        return this._rater;
    }

    public removeRater() {
        this._parent.removeRater(this._rater);
    }

    public onDeliveryChanged(event: MdButtonToggleChange): void {
        this._rater.deliveryType = event.value;
    }

    public deliveryMethodText() : string {
        let del = this.deliveryMethods.find(it => it.value == this._rater.deliveryType);
        return del ? del.label : '';
    }

    public enableForEditing() {
        this._rater.editing = true;        
    }

    public collapse() {
        this._rater.editing = false;
    }
}