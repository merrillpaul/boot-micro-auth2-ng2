import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Subject } from "rxjs/Subject";

import { FormDetailSelectionItem } from '../dto/form-detail-selection';
import { FormSelectionItem } from '../dto/form-selection';
import { HttpService } from '../../shared/shared.module';
import { environment } from "../../../environments/environment";

const MOCK_INSTRUMENTS: FormSelectionItem[] =
    [
        {
            id: 'I1000',
            name: 'BASC-3 PRS-Child',

        },
        {
            id: 'I11111',
            name: 'BASC-3 SRP-Child'
        },
        {
            id: 'I1002',
            name: 'BASC-3 TRS-Child'
        },
        {
            id: 'I1003',
            name: 'CVLT-II-Standard'
        },
        {
            id: 'I1004',
            name: 'D-KEFS'
        },
        {
            id: 'I1005',
            name: 'D-REF Parent Form'
        },
        {
            id: 'I1006',
            name: 'WAIS-V',
        },
        {
            id: 'I1007',
            name: 'WIAT-III',
        },
        {
            id: 'I1008',
            name: 'WISC-V',
        }
        ,
        {
            id: 'I1009',
            name: 'WMS-IV Adult'
        }
    ];

@Injectable()
export class ExamineeAssessmentService {

    private _cachedFormSelectionItems: FormSelectionItem[];
    constructor(private _http: HttpService) { }

    getInstruments(): Observable<FormSelectionItem[]> {
        let res: Subject<FormSelectionItem[]> = new Subject();
        this._cachedFormSelectionItems = [];
        this._http.get(`${environment.globalServerBaseUrl}/api/form/list/lite`).subscribe((resp: any) => {
            if (!!resp) {
                let forms: FormSelectionItem[] = resp;
                this._cachedFormSelectionItems = forms;
                res.next(forms);
            } else {
                res.error("error");
            }

            res.complete();
        }, err => { 
            res.error(err); 
            res.complete(); 
        });

        return res.asObservable();
    }


    search(name: string): Observable<FormSelectionItem[]> {
        return Observable.of(this._cachedFormSelectionItems.filter(it => it.name.toLocaleLowerCase().indexOf(name.toLocaleLowerCase()) >= 0));
    }

    getInstrument(id: string): Observable<FormDetailSelectionItem> {
        let res: Subject<FormDetailSelectionItem> = new Subject();
        this._http.get(`${environment.globalServerBaseUrl}/api/form/${id}/detail`).subscribe((resp: any) => {
            if (!!resp) {
                let formDetail: FormDetailSelectionItem = resp;
                // TODO remove
                formDetail.formType = 'RATER';
                res.next(formDetail);
            } else {
                res.error("error");
            }

            res.complete();
        }, err => { res.error(err); res.complete(); });
        
        return res.asObservable();
    }

}



/*


getExamineeEmitter() {
        this.examineeService.getExamineeEmitter();
    }

    save(examineeAssessment: ExamineeAssessment): Observable<Response> {
        return this.http.put(this.getExamineeSaveUrl(), examineeAssessment);
    }

    getExamineeSaveUrl() {
        return environment.customerServerBaseUrl + "/api/examineeAssessment";
    }


    listExamineeAssessments(id: string): Observable<any> {
        return this.http.get(this.getListUrl(id));
    }

    getListUrl(id: string) {
        return environment.customerServerBaseUrl + `/api/examineeAssessment/list/${id}`;
    }

    delete(id: string) {
        return this.http.delete(this.prepareDeleteUrl(id));
    }

    private prepareDeleteUrl(id: string) {
        return environment.customerServerBaseUrl + `/api/examineeAssessment/${id}`;
    }

    getSubTests(assessment: string): Observable<any> {

        return Observable.of(["subtest1", "subtest2", "subtest3", "subtest4", "subtest5", "subtest6"]);
        // return Observable.fromPromise(
        //   Promise
        //   .resolve(true)
        //   .then(() => Object.assign({}, ["subtest1", "subtest2", "subtest3", "subtest4", "subtest5", "subtest6"])));

        //return this.http.get(this.prepareSubTestsUrl(assessment));
    }


    getAssessments(): Observable<any> {
        return Observable.of(["BASC-3", "16-PF", "GFTA", "BASC-3", "16-PF", "GFTA", "BASC-3", "16-PF", "GFTA", "BASC-3", "16-PF", "GFTA"]);
        //return this.http.post(this.prepareSubTestsUrl(), {});
    }

    private prepareSubTestsUrl() {
        return environment.globalServerBaseUrl + `/api/assessment/list`;
    }


    searchExamineeAssessments(text: string) {
        return this.http.get(this.prepareSearchUrl(text));
    }

    prepareSearchUrl(text: string) {
        return environment.customerServerBaseUrl + `/api/examineeAssessment/search/${text}`;
    }

    */