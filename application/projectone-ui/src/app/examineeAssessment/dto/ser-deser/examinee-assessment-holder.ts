import { ExamineeAssessment } from './examinee.assessment';

export interface ExamineeAssessmentHolder {
    groupId: string;
    examineeAssessments: ExamineeAssessment[];
}
