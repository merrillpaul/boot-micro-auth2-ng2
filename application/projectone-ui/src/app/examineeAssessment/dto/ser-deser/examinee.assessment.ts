import { BundleVariable } from './bundle.variable';
import { Rater } from '../rater';

export interface ExamineeAssessment {
     version?:number;
     id?: string;
     formId: string;
     adminstrationDate: number;
     examinerId?: string;
     deliveryTypeId?:string;
     status?: string;
     subtestIds?: string[];
     bundleVariables?: BundleVariable[];
     rater?: Rater;
}