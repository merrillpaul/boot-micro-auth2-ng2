import { FormTypeAssessmentConverter } from './form-type.converter';
import { InstrumentAssessment, ExamineeAssessment } from '../../dto';

export class RaterFormTypeAssessmentConverter implements FormTypeAssessmentConverter {
    
    convert(uiAssessment: InstrumentAssessment): ExamineeAssessment[] {
        let assessments: ExamineeAssessment[] = [];
        uiAssessment.raters.forEach(rater => {
            assessments.push({
                 formId: uiAssessment.instrument.id,
                 adminstrationDate: Date.now(), // TODO get from a future date field                 
                 deliveryTypeId: rater.deliveryType,
                 rater: rater
            });
        });
        return assessments;
    }

}