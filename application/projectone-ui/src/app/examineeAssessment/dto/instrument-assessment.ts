import { FormDetailSelectionItem } from './form-detail-selection';
import { Rater } from './rater';
import { BehaviorSubject } from 'rxjs/Rx';

export interface InstrumentAssessment {
    editing?:boolean;
    completed?:boolean;
    currentSelected?:boolean;
    instrument: FormDetailSelectionItem;
    raters?: Rater[];
    valid: BehaviorSubject<boolean>;
}