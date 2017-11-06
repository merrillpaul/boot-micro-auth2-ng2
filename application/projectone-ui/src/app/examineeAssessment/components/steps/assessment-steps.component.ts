import { Component, OnDestroy, AfterContentInit, ContentChildren, QueryList, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';

import { AssessmentStepComponent } from './assessment-step.component';


export interface StepChangeEvent {
    newStep: AssessmentStepComponent;
    prevStep: AssessmentStepComponent;
}
@Component({
    selector: 'assessment-steps',
    templateUrl: './assessment-steps.component.html',
    styleUrls: ['./assessment-steps.component.scss']
})
export class AssessmentStepsComponent implements OnDestroy, AfterContentInit {
    constructor() { }

    private _steps: QueryList<AssessmentStepComponent>;
    private _subcriptions: Subscription[];

    @Output('stepChange') onStepChange: EventEmitter<StepChangeEvent> = new EventEmitter<StepChangeEvent>();

    @ContentChildren(AssessmentStepComponent)
    set stepsContent(steps: QueryList<AssessmentStepComponent>) {
        if (steps) {
            this._steps = steps;
            this._registerSteps();
        }
    }

    get steps(): AssessmentStepComponent[] {
        return this._steps.toArray();
    }
    prevStep: AssessmentStepComponent;
    /**
     * Executed after content is initialized, loops through any Step children elements,
     * assigns them a number and subscribes as an observer to their [onActivated] event.
     */
    ngAfterContentInit(): void {
        this._registerSteps();
    }

    /**
     * Unsubscribes from Step children elements when component is destroyed.
     */
    ngOnDestroy(): void {
        this._deregisterSteps();
    }

    areStepsActive(): boolean {
        return this._steps.filter((step: AssessmentStepComponent) => {
            return step.active;
        }).length > 0;
    }


    private _registerSteps(): void {
        this._subcriptions = [];
        let stepC = this._steps.length;
        this._steps.toArray().forEach((step: AssessmentStepComponent, index: number) => {
            if ( index + 1 === stepC ) {
                step.last = true;
            }
            let subscription: Subscription = step.onActivated.asObservable().subscribe(() => {
                this._onStepSelection(step);
            });
            this._subcriptions.push(subscription);
        });
    }

    private _deactivateAllBut(activeStep: AssessmentStepComponent): void {
        this._steps.filter((step: AssessmentStepComponent) => step !== activeStep)
            .forEach((step: AssessmentStepComponent) => {
                step.active = false;
            });
    }

    private _onStepSelection(step: AssessmentStepComponent): void {
        if (this.prevStep !== step) {
            let prevStep: AssessmentStepComponent = this.prevStep;
            this.prevStep = step;
            let event: StepChangeEvent = {
                newStep: step,
                prevStep: prevStep,
            };
            this._deactivateAllBut(step);
            this.onStepChange.emit(event);
        }
    }

    private _deregisterSteps(): void {
        if (this._subcriptions) {
            this._subcriptions.forEach((subs: Subscription) => {
                subs.unsubscribe();
            });
            this._subcriptions = undefined;
        }
    }
}