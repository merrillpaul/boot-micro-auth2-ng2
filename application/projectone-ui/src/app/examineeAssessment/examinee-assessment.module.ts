import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../shared/shared.module';

import { BuildExamineeAssessmentComponent } from './components/build-assessment.component';
import { AssessmentStepsComponent } from './components/steps/assessment-steps.component';
import { AssessmentStepComponent } from './components/steps/assessment-step.component';
import { AssessmentNotStartedComponent, AssessmentCompletedComponent, AssessmentActiveComponent } from './components/steps/assessment-step.component';
import { AssessmentExamineeComponent } from './components/examinee/assessment-examinee.component';
import { 
    AssessmentBuilderContainerComponent, 
    AssessmentInstrumentBuilderComponent, 
    AssessmentRaterBuilderComponent ,
    AssessmentRaterFormComponent
} from './components/assessment-builder/builder.component';


import { ExamineeAssessmentRoutingModule } from './examinee-assessment.routing.module';
import { ExamineeModule } from '../examinee/examinee.module';

import { ExamineeResolver } from './services/examinee.resolver';
import { ExamineeAssessmentService } from './services/examinee-assessment.service'
import { AssessmentSerDeserConverterService } from './services/assessment-serdeser.converter.service';

export { ExamineeResolver } from './services/examinee.resolver';
export { ExamineeAssessmentService } from './services/examinee-assessment.service'



@NgModule({
    imports: [
        SharedModule, CommonModule, ExamineeAssessmentRoutingModule, ExamineeModule
    ],
    exports: [],
    declarations: [
        BuildExamineeAssessmentComponent,
        AssessmentStepsComponent,
        AssessmentStepComponent,
        AssessmentNotStartedComponent,
        AssessmentCompletedComponent,
        AssessmentExamineeComponent,
        AssessmentActiveComponent,
        AssessmentBuilderContainerComponent,
        AssessmentInstrumentBuilderComponent,
        AssessmentRaterBuilderComponent,
        AssessmentRaterFormComponent
    ],
    providers: [
        ExamineeResolver,
        ExamineeAssessmentService,
        AssessmentSerDeserConverterService
    ],
})
export class ExamineeAssessmentModule { }
