import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { ExamineeService } from '../../examinee/examinee.module';

@Injectable()
export class ExamineeResolver implements Resolve<any> {
  constructor(
    private _examineeService: ExamineeService
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<any> {
    return this._examineeService.getExaminee(route.params.examineeId);
  }
}