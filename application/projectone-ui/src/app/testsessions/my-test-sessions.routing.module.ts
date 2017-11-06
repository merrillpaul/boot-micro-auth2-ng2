import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MyTestSessionsHomeComponent } from './components/test-sessions.home.component';
import { TestSessionDetailComponent } from './components/detail/test-session.detail.component';


const routes: Routes = [
  {
    path: 'home', component: MyTestSessionsHomeComponent, children: [
      { path: 'recent', component: TestSessionDetailComponent },
      { path: 'pending', component: TestSessionDetailComponent }
    ]    
  },
  {
    path: '', redirectTo: 'home/recent', pathMatch: 'full', 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MyTestSessionsRoutingModule { }

