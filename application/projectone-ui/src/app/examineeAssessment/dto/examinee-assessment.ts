import { Examinee } from '../../examinee/examinee.module'
import { InstrumentAssessment } from './instrument-assessment';

export interface ExamineeAssessmentSession {
    id?: string;
    examinee?: Examinee;
    instrumentAssessments?: InstrumentAssessment[];
    valid?: boolean
}