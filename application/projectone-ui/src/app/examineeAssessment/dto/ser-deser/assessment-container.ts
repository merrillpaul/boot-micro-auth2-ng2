import { ExamineeAssessmentHolder } from './examinee-assessment-holder'; 
export interface ExamineeAssessmentContainer {
    examineeId: string;
    examineeAssessmentHolders: ExamineeAssessmentHolder[];
}