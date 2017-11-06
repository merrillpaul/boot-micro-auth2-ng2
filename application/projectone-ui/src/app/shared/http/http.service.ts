import { Injectable } from "@angular/core";
import { Http, Response, RequestOptionsArgs, Headers, URLSearchParams } from "@angular/http";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import { TokenService } from "../../core/token/token.service";
import { CoreEventService } from "../../core/services/core.event.service";

@Injectable()
export class HttpService {

    public headers: any = {};
    protected baseUrl: string = '';

    constructor(protected http: Http, protected tokenService: TokenService, private _eventService: CoreEventService) {
    }

    setTokenAuthorizationHeader() {
        if (this.tokenService.hasValidToken()) {
            this.setDefaultContentType();
            this.setHeader('Authorization', 'Bearer ' + this.getAccessToken());            
        } else {
            this.removeHeader('Authorization');
            this.removeDefaultContentType('Content-Type');
        }
    }

    setDefaultContentType() {
        this.setHeader('Content-Type', 'application/json');
    }

    removeDefaultContentType(key: string) {
        delete this.headers[key];
    }

    setHeader(key: string, value: string) {
        this.headers[key] = value;
    }

    setBaseUrl(url: string) {
        this.baseUrl = url;
    }

    removeHeader(key: string) {
        delete this.headers[key];
    }


    get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.get(this.generateUrl(url), this.generateOptions(options))
            .map(this.responseHandler, this)
            .catch(err => this.handleError(err));
    }

    post(url: string, data: Object, options?: RequestOptionsArgs): Observable<Response> {
        let localData = this.prepareData(data);
        return this.http.post(this.generateUrl(url), localData, this.generateOptions(options))
            .map(this.responseHandler, this)
            .catch(err => this.handleError(err));
    }

    put(url: string, data: Object, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.put(this.generateUrl(url), JSON.stringify(data), this.generateOptions(options))
            .map(this.responseHandler, this)
            .catch(err => this.handleError(err));
    }

    patch(url: string, data: Object, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.put(this.generateUrl(url), JSON.stringify(data), this.generateOptions(options))
            .map(this.responseHandler, this)
            .catch(err => this.handleError(err));
        ;
    }

    delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.delete(this.generateUrl(url), this.generateOptions(options))
            .map(this.responseHandler, this)
            .catch(err => this.handleError(err));
    }

    protected responseHandler(response: Response): Response {

        if (response.status == 204) {
            return null;
        }
        if (Math.floor(response.status / 100) != 2) {
            this.handleError(response);
        }

        if (!!response.text()) {
            return response.json();
        }
        return response;
    }


    private handleError(error: Response | any) {
        let errMsg: string;
        let errorCode: number = 500;
        if (error instanceof Response) {           
            errorCode = error.status;
            // TODO check for 401 or 403 and fire a global event to
            // handle timeouts on session or tokens
        
            if (errorCode === 401 || errorCode === 403) {
                //handle authorization errors
                //in this example I am navigating to logout route which brings the login screen
                this._eventService.fireTimedout();
            }
            
        }
        return Observable.throw(errorCode);
    }

    protected generateUrl(url: string): string {
        return !!url.match(/^((?:http(|s):\/\/www\.)|(?:http:\/\/))/) ? url : this.baseUrl + url;
    }

    protected generateOptions(options: RequestOptionsArgs = {}): RequestOptionsArgs {
        if (!options.headers) {
            options.headers = new Headers();
        }
        this.setTokenAuthorizationHeader();

        Object.keys(this.headers)
            .filter((key) => this.headers.hasOwnProperty(key))
            .forEach((key) => {
                options.headers.append(key, this.headers[key]);
            });

        return options;
    }

    private getAccessToken() {
        return this.tokenService.getAccessToken();
    }

    private prepareData(data: Object): any {
        let localData = null;
        if (data instanceof URLSearchParams) {
            localData = data;
        } else {
            localData = JSON.stringify(data)
        }
        return localData;
    }
}
