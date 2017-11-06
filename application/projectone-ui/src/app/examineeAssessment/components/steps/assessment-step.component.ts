import { Component, OnInit, Input, Output, EventEmitter, ElementRef, Renderer2 } from '@angular/core';



@Component({
    selector: 'div[assessment-not-started-step]',
    template: '<div class="assessment-not-started tc-disabled"><ng-content></ng-content></div>',
    styles: [
    ]
})
export class AssessmentNotStartedComponent  {
    
}

@Component({
    selector: 'div[assessment-step-completed]',
    template: '<ng-content></ng-content>'
})
export class AssessmentCompletedComponent  {
    
}

@Component({
    selector: 'div[assessment-active-step]',
    template: '<ng-content></ng-content>'
})
export class AssessmentActiveComponent  {
    
}


@Component({
    selector: 'assessment-step',
    templateUrl: './assessment-step.component.html',
    styleUrls: ['./assessment-step.component.scss']
})
export class AssessmentStepComponent {
    constructor(
        private _renderer: Renderer2,
        private _elementRef: ElementRef
    ) { }

    private _active: boolean = false;
    private _completed: boolean = false;
    private _last: boolean = false;

    @Output('activated') onActivated: EventEmitter<void> = new EventEmitter<void>();

    @Output('deactivated') onDeactivated: EventEmitter<void> = new EventEmitter<void>();

    @Output('completed') onCompleted: EventEmitter<void> = new EventEmitter<void>();

    @Output('resetCompleted') onResetCompleted: EventEmitter<void> = new EventEmitter<void>();

    @Input('active')
    set active(active: boolean) {
        this._setActive(active);
    }
    get active(): boolean {
        return this._active;
    }

    @Input('completed')
    set complete(complete: boolean) {
        this._setCompleted(complete);
    }
    get complete(): boolean {
        return this._completed;
    }

    set last(last: boolean) {
        this._setLast(last);
    }

    get last(): boolean {
        return this._last;
    }

    private _setLast(last: boolean) {
        this._last = last;
        this._renderer.removeClass(this._elementRef.nativeElement, 'last');
        if (last) {
            this._renderer.addClass(this._elementRef.nativeElement, 'last');            
        }
    }


    private _setCompleted(newCompleted: boolean) {
        if (this._completed !== newCompleted) {
            this._completed = newCompleted;
            if (newCompleted) {
                this._onCompleted();
            } else {
                this._onResetCompleted();
            }

        }
    }

    private _setActive(newActive: boolean): boolean {
        if (this._active !== newActive) {
            this._active = newActive;
            if (newActive) {
                this._onActivated();
            } else {
                this._onDeactivated();
            }
            return true;
        }
        return false;
    }

    private _onActivated(): void {
        this.onActivated.emit(undefined);
    }

    private _onDeactivated(): void {
        this.onDeactivated.emit(undefined);
    }

    private _onCompleted(): void {
        this.onCompleted.emit(undefined);
    }

    private _onResetCompleted(): void {
        this.onResetCompleted.emit(undefined);
    }
}