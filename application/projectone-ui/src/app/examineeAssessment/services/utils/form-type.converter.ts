import { ExamineeAssessment, InstrumentAssessment } from '../../dto';

export interface FormTypeAssessmentConverter {
    convert(uiAssessment: InstrumentAssessment ) : ExamineeAssessment[] | ExamineeAssessment;
}