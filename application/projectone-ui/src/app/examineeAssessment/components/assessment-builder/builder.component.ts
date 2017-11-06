import { Component, AfterViewInit, AfterContentInit, Input } from '@angular/core';
import { Observable, Subject, BehaviorSubject } from "rxjs/Rx";

import { MediaQueryService, SpinnerService, DialogService } from '@pearson/angular-material';
import { FadeOnEnterAnimation } from '@pearson/angular-material';
import { enterAnimation } from '../../../../app/app.animations';

import { 
    FormSelectionItem, 
    FormDetailSelectionItem, 
    ExamineeAssessmentSession,
    CreateAssessmentContainerRequest
 } from '../../dto';


import { ExamineeAssessmentService } from '../../services/examinee-assessment.service';

import { AssessmentSerDeserConverterService } from '../../services/assessment-serdeser.converter.service';
export { AssessmentInstrumentBuilderComponent } from './instrument/instrument-builder';
export { AssessmentRaterBuilderComponent } from './rater/rater-builder';
export { AssessmentRaterFormComponent } from './rater/rater-form';

@Component({
    selector: 'assessment-builder-container',
    templateUrl: './builder.component.html',
    styleUrls: [
        './builder.component.scss'
    ],
    animations: [
        FadeOnEnterAnimation(500),
        enterAnimation
    ],
})
export class AssessmentBuilderContainerComponent implements AfterContentInit {

    instruments: FormSelectionItem[];
    currentSelectedInstrument: FormSelectionItem;
    searchWord: string = '';
    private searchTerms = new Subject<string>();
    errorInstruments:boolean = false;
    errorSelectInstrument: boolean = false;

    private _examineeAssessment: ExamineeAssessmentSession;


    constructor(
        public media: MediaQueryService,
        private _spinnerService: SpinnerService,
        private _dialogService: DialogService,
        private _converterService: AssessmentSerDeserConverterService,
        private _examineeAssessmentService: ExamineeAssessmentService) { }


    @Input('examineeAssessment')
    public set examineeAssessment(examineeAssessment: ExamineeAssessmentSession) {
        this._examineeAssessment = examineeAssessment;
    }

    public get examineeAssessment(): ExamineeAssessmentSession {
        return this._examineeAssessment;
    }

    public get hasInstruments(): boolean {
        return this._examineeAssessment.instrumentAssessments.length > 0;
    }

    search(term: string): void {
        this.searchTerms.next(term);
    }

    ngAfterContentInit(): void {
        
        this._spinnerService.resolve('form.details.load');      
        this.fetchInstrumentList();  
        this.searchTerms
            .debounceTime(800)
            .distinctUntilChanged()
            .subscribe(data => this.searchInstruments());
    }


    fetchInstrumentList() {
        this._spinnerService.register('instruments.load');
        this.instruments = [];
        this.errorInstruments = false;
        this._examineeAssessmentService.getInstruments().subscribe((data: FormSelectionItem[]) => {
            this.instruments = data;
            setTimeout(() => this._spinnerService.resolve('instruments.load'), 100);
        }, err => {
            this.instruments = [];
            this._spinnerService.resolve('instruments.load');
             this.errorInstruments = true;
        });
    }

    selectAndAddInstrument(item: FormSelectionItem): void {
        this.currentSelectedInstrument = item;
        this._examineeAssessment.instrumentAssessments.forEach(it => {
           it.currentSelected = false;
        });
        let existingInstrumentAssessment = this._examineeAssessment.instrumentAssessments.find(it => it.instrument.id === item.id);
        if (existingInstrumentAssessment) {           
            existingInstrumentAssessment.currentSelected = true;
        } else {
            this.errorSelectInstrument = false;
            this._spinnerService.register('form.details.load');
            item.detailsLoading = true;
            this._examineeAssessmentService.getInstrument(item.id).subscribe((form: FormDetailSelectionItem) => {
                setTimeout(() => {
                    this._examineeAssessment.instrumentAssessments.push({
                        editing: true,
                        currentSelected: true,
                        instrument: form,
                        valid: new BehaviorSubject<boolean>(false),
                        raters: []                
                    });
                    this._spinnerService.resolve('form.details.load');
                    item.detailsLoading = false;
                }, 300);
            }, (err => {
                setTimeout(() => this._spinnerService.resolve('form.details.load'), 400);
                item.detailsLoading = false;
                this.errorSelectInstrument = true;
            }));           
        }
    }

    public activateBattery(): void {
        let res: CreateAssessmentContainerRequest = this._converterService.createAssessmentRequest(this._examineeAssessment);
        console.log(res);
        console.log(JSON.stringify(res.payload, null, 5));
        alert (JSON.stringify(res.payload, null, 5));
    }

    searchInstruments() {

        if (this.searchWord.length === 0) {
            this.fetchInstrumentList();
            return;
        }

        this._spinnerService.register('instruments.load');
        this._examineeAssessmentService.search(this.searchWord).debounceTime(200).subscribe(resp => {
            if (!!resp) {
                let e: FormSelectionItem[] = resp;
                this.instruments = e;

            } else {
                this.instruments = [];
            }
            setTimeout(() => this._spinnerService.resolve('instruments.load'), 500);
        });
    }

    allInstruments(): void {
        this.searchWord = '';
        this.fetchInstrumentList();
    }

    onInstrumentRemoved(removedItem: FormDetailSelectionItem) {
        this._dialogService.warning({
            title: `Remove ${removedItem.assessmentName}?`,            
            message: 'Are you sure to remove this instrument?'
        }, {
               // width: '40%'
            }).subscribe((res: any) => {
                if (res === 'ok') {
                    this._examineeAssessment.instrumentAssessments = this._examineeAssessment.instrumentAssessments.filter(it=> it.instrument.id !== removedItem.id);
                    if (this.currentSelectedInstrument && removedItem.id === this.currentSelectedInstrument.id) {
                        this.currentSelectedInstrument = null;
                    }
                }
            });
    }


    public get validBattery(): boolean {
        if ( this._examineeAssessment ) {
           let assessments = this._examineeAssessment.instrumentAssessments;
           if ( assessments.length === 0 ) {
               return false;
           } else {
               return assessments.every(it => { 
                  return  it.valid.getValue() === true && it.completed === true;
                });
           }

        } else {
            return false;
        }
    }
}