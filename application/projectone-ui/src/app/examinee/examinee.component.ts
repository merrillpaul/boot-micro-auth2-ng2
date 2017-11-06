import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import "rxjs/add/observable/of";
import { Observable, Subject } from "rxjs/Rx";
import { Examinee } from "./dto/examinee";
import { ExamineeService } from "./services/examinee.service";
import {
    DialogService,
    GridColumnMetadata,
    GridColumnSortChangeEvent,
    SortOrder,
    PageChangeEvent,
    GridRowClickEvent,
    GridCellClickEvent,
    SpinnerService,
    FadeOnEnterAnimation,
    FadeInOutAnimation
} from '@pearson/angular-material';
import { fadeRouteAnimation } from '../app.animations';

const DATE_FORMAT: (v: any) => any = (v: number) => {
    return '05/22/2000';
};

@Component({
    selector: 'examinee',
    templateUrl: './examinee.component.html',
    styleUrls: ['./examinee.component.scss'],
    animations: [
        FadeOnEnterAnimation(1000),
        fadeRouteAnimation
    ],
     host: {'[@routeAnimation]': ''}
})
export class ExamineeComponent implements OnInit {
    title: string = "Examinee Management"
    examinees: Observable<Examinee[]>;
    examineesData: Examinee[];
    private searchTerms = new Subject<string>();
    confirmationMessage: string;
    error: boolean = false;

    activatedRouteSub: any;
    examineeId: string;

    searchResult: Observable<Array<Examinee>>;
    searchWord: string = "";
    columns: GridColumnMetadata[] = [

        {
            name: "firstName",
            label: "First Name"
        },
        {
            name: 'lastName',
            label: "Last Name"

        },
        {
            name: 'middleName',
            label: "Middle Name"
        },
        {
            name: 'gender',
            label: "Gender"

        },
        {
            name: "dob",
            label: "DOB"
        },
        {
            name: "actions",
            label: ""
        }
    ];


    actionResponse: string = '';

    constructor(private examineeService: ExamineeService, private router: Router, protected activatedRoute: ActivatedRoute, private _spinnerService: SpinnerService) {

    }

    search(term: string): void {
        this.searchTerms.next(term);
    }
    ngOnInit() {
        this.fetchExamineeList();
        this.searchTerms
            .debounceTime(1000)
            .distinctUntilChanged()
            .subscribe(data => this.searchExaminees());

    }

    fetchExamineeList() {
        this._spinnerService.register('examinees.load');
        let result: Observable<any> = this.examineeService.list();
        this.error = false;
        result.subscribe(resp => {
            if (!!resp) {
                let e: Examinee[] = resp;
                this.examineesData = e;
                this.examinees = Observable.of(e);
            }
            setTimeout(() => this._spinnerService.resolve('examinees.load'), 1000);
        }, err => {
            this.error = true;
            setTimeout(() => this._spinnerService.resolve('examinees.load'), 1000);
        });

    }

    buildAssessment(examineeIdLocal: string) {
        this.router.navigate(['/build', examineeIdLocal]);
    }

    new() {
        this.examineeService.setCurrentExaminee(new Examinee());
        this.router.navigate(['/examinee/new']);
    }


    delete(id: string) {
        this.examineeService.delete(id).subscribe(response => {
            this.actionResponse = 'Examinee deletion successful';
            this.fetchExamineeList();
        }, error => {
            this.actionResponse = 'Examinee deletion failed';
        });
    }

    edit(id: string) {

    }

    searchExaminees() {

        if (this.searchWord.length === 0) {
            this.fetchExamineeList();
            return;
        }
        this.error = false;
        this.examineeService.search(this.searchWord).debounceTime(4000).subscribe(resp => {
            if (!!resp) {
                let e: Examinee[] = resp;
                this.examineesData = e;
                this.examinees = Observable.of(e);
            } else {
                this.examineesData = [];
                this.examinees = Observable.of([]);
            }
        }, err => {
            this.error = true;
        });
    }


}
