import { Component, Input, Output, EventEmitter, forwardRef, Inject } from '@angular/core';

import { FadeOnEnterAnimation, DialogService } from '@pearson/angular-material';
import { Subject, Observable } from 'rxjs/Rx';

import { Logger } from '../../../../core/core.module';
import { Rater } from '../../../dto/rater';
import { RaterRemovedEvent } from '../../../dto/rater.event';


import { IBuilderComponent } from '../builder.interface';

import { AssessmentInstrumentBuilderComponent } from '../instrument/instrument-builder';

@Component({
    selector: 'assessment-rater-builder',
    templateUrl: './rater-builder.html',
    styleUrls: ['./rater-builder.scss'],
    animations: [
        FadeOnEnterAnimation(500)
    ],
})
export class AssessmentRaterBuilderComponent  {


    private _raters: Rater[];
    private _logger: Logger;

    constructor(private _dialogService: DialogService,
         @Inject(forwardRef(() => AssessmentInstrumentBuilderComponent)) private _parent: AssessmentInstrumentBuilderComponent
    ) { 
        this._logger = Logger.getLogger(AssessmentRaterBuilderComponent);
    }

    @Output('remove')
    removed: EventEmitter<RaterRemovedEvent> = new EventEmitter<RaterRemovedEvent>();   

    @Output('builderValidityChange')
    validityChanges: EventEmitter<boolean> = new EventEmitter<boolean>();

    @Input()
    editing: boolean;

    @Input('raters')
    public set raters(raters: Rater[]) {
        this._raters = raters;
    }

    public get raters(): Rater[] {
        return this._raters;
    }

    public addRater(): void {
        this._raters = this._raters || [];
        this._raters.forEach(it => it.editing = false);
        let rater = {
            editing: true,
            valid: false
        };
        this._raters.push(rater);
        
    }

    public removeRater(rater: Rater) {
        this._dialogService.warning({
            title: `Remove ${rater.firstName}?`,
            message: 'Are you sure to remove this rater?'
        }, {
              //  width: '40%'
            }).subscribe((res: any) => {
                if (res === 'ok') {
                    this._raters = this._raters.filter(it=> it !== rater);
                    this.removed.emit({
                        removedRater: rater,
                        remainingRaters: this._raters
                    });
                    this.validityChanges.emit(this.allValid);   
                    this._markInstrumentState();
                }
            });
        
    }

    public get allValid(): boolean {
        if ( this._raters.length === 0 ) {
            return true;
        }
        return this._raters.every(it => it.valid);       
    }

    public onValidityChange(rater: Rater): void {
        this.validityChanges.emit(this.allValid);
        this._markInstrumentState();
        
    }


    public get formDeliveryTypes(): string[] {
        return this._parent.instrument.formDeliveryTypes || [];
    }

    private _markInstrumentState(): void {
        if ( this._raters.length === 0) {
             this._parent.instrumentAssessment.valid.next(false);
             return ;   
        }
        this._parent.instrumentAssessment.valid.next(this.allValid);        
    }   

}