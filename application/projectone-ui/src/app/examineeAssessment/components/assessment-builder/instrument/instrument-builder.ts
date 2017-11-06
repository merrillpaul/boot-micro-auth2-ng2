import { Component, Input, Output, EventEmitter, ViewChild, AfterContentInit } from '@angular/core';

import { FadeOnEnterAnimation } from '@pearson/angular-material';
import { ExpandingPanelComponent, DialogService } from '@pearson/angular-material';

import { FormDetailSelectionItem } from '../../../dto/form-detail-selection';
import { InstrumentAssessment } from '../../../dto/instrument-assessment'
import { Rater } from '../../../dto/rater';
import { RaterRemovedEvent } from '../../../dto/rater.event';
import { AssessmentRaterBuilderComponent } from '../rater/rater-builder';

@Component({
    selector: 'assessment-instrument-builder',
    templateUrl: './instrument-builder.html',
    styleUrls: ['./instrument-builder.scss'],
    animations: [
        FadeOnEnterAnimation(1000)
    ],
})
export class AssessmentInstrumentBuilderComponent implements AfterContentInit {

    constructor(
        private _dialogService: DialogService
    ) { }

    private _instrumentAssesmment: InstrumentAssessment;
    private _collapsed: boolean = false;
    private _instrumentValid: boolean = false;
    _validRaters: boolean = true; // initially when there are no raters 


    @ViewChild(ExpandingPanelComponent) expandingPanel: ExpandingPanelComponent;

    @ViewChild(AssessmentRaterBuilderComponent) raterBuilder: AssessmentRaterBuilderComponent;

    @Output('removed')
    removed: EventEmitter<FormDetailSelectionItem> = new EventEmitter<FormDetailSelectionItem>();

    @Input('instrumentAssessment')
    public set instrumentAssessment(instrumentAssessment: InstrumentAssessment) {
        this._instrumentAssesmment = instrumentAssessment;
    }

    public get instrumentAssessment(): InstrumentAssessment {
        return this._instrumentAssesmment;
    }

    public get instrument(): FormDetailSelectionItem {
        return this._instrumentAssesmment.instrument;
    }

    ngAfterContentInit(): void {
        this._instrumentAssesmment.valid.subscribe(valid => {
            //console.log('Instrument assessmwent valid for ', this._instrumentAssesmment, data);
            this.setValidInstrument(valid)
        });
    }


    openForEditing(event: Event): void {

        if (this.expandingPanel.expand) {
            event.stopPropagation();
        }

        this._instrumentAssesmment.editing = true;
        this._instrumentAssesmment.completed = false;
        //this._instrumentAssesmment.raters.forEach(it => it.editing = true);
        this._collapsed = false;
        
    }


    onCollapsed(): void {
        this._instrumentAssesmment.currentSelected = false;
        this._collapsed = true;
    }

    onRemove(): void {
        this.removed.emit(this.instrument);
    }

    doneWithInstrument(): void {
        this._instrumentAssesmment.editing = false;
        this._instrumentAssesmment.completed = true;
        this._instrumentAssesmment.raters.forEach(it => it.editing = false);
        
    }

    public get completed(): boolean {
        return this._instrumentAssesmment.completed;
    }

    public get color(): string {
        //if (this._completed) {
        // return "success";
        //} else {
       // return this.instrumentAssessment.currentSelected ? 'primary' : 'secondary';
        return 'secondary';
        //}
    }

    @Input('index')
    index: number;

    public addRater(): void {
        this.raterBuilder.addRater();
    }

    public removeRater(event: RaterRemovedEvent) {
        this.instrumentAssessment.raters = event.remainingRaters;
    }

    public setValidInstrument(validity: boolean) {
        this._instrumentValid = validity;
    }

    public get validInstrument(): boolean {
        return this._instrumentValid;
    }


    public markValidRater(valid: boolean) {
        this._validRaters = valid;
    }

    public get validRaters(): boolean {
        return this._validRaters
    }
}