import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ExamineeResolver } from './services/examinee.resolver';
import { BuildExamineeAssessmentComponent } from './components/build-assessment.component';

const routes: Routes = [
  { path: '', component: BuildExamineeAssessmentComponent },
  { path: ':examineeId', component: BuildExamineeAssessmentComponent , resolve: {
    examinee: ExamineeResolver
  }}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ExamineeAssessmentRoutingModule { }

