import * as http from 'http';
import * as https from 'https';
import * as Qs from 'querystring';
import * as Url from 'url';
import * as Request from 'request';

import { ClientRequest } from 'http';


interface HttpArguments {
    url: string;
    username?: string;
    password?: string;
    headers?: any    
}

interface HttpPostArguments extends HttpArguments {    
    data?: any
}


export class Http {

    static parseUrl(urls: string): any {
        if (urls.indexOf("https://") < 0 && urls.indexOf("http://") < 0) {
            urls = "http://" + urls;
        }
        let obj = Url.parse(urls);
        let opts = {
            protocol: obj.protocol,
            host: obj.hostname,
            port: obj.port || (obj.protocol === 'https:' ? 443 : 80),
            path: obj.path
        };
        return opts;
    }

    static post(arg: HttpPostArguments): Promise<any> {
        return Http.postRequest(arg);
    }

    static postFormData(arg: HttpPostArguments): Promise<any> {
        return Http.postRequest(arg, true);
    }

    static get(arg: HttpArguments): Promise<any> {
        let url: string = arg.url;
        let username: string = arg.username;
        let password: string = arg.password;
        let headers: any = arg.headers;

         let promise = new Promise<any>((resolve, reject) => {
            let opts = Http.parseUrl(url);
            opts.method = 'GET';
            opts.headers = {               
                "User-Agent": "Node http tool"
            };            
            if (headers !== undefined) {
                opts.headers = Object.assign(opts.headers, headers);
            }

            if (username && password) {
                opts.auth = username + ":" + password;
            }
            let callback = res => {
                let buf = "";
                res.on("data", function (d) {
                    buf += d;
                });
                res.on("end", function () {
                    resolve(buf);
                });
                res.on("error", function (e) {
                    reject(e);
                });
            };
            let getRequest: ClientRequest;
            if (opts.protocol === "https:") {
                getRequest = https.request(opts, callback);
            } else {
                getRequest = http.request(opts, callback);
            }           
            getRequest.end();

        });
        return promise;

    }

    static getRaw(arg: HttpArguments, callback: Function, errorCallback?: Function): void {
        let url: string = arg.url;
        let username: string = arg.username;
        let password: string = arg.password;
        let headers: any = arg.headers;
        let opts = Http.parseUrl(url);
        opts.method = 'GET';
        opts.headers = {               
            "User-Agent": "Node http tool"
        };            
        if (headers !== undefined) {
            opts.headers = Object.assign(opts.headers, headers);
        }

        if (username && password) {
            opts.auth = username + ":" + password;
        }
        let getRequest: ClientRequest;
        if (opts.protocol === "https:") {
            getRequest = https.request(opts, (response) => {
                callback(response);
            });
        } else {
             getRequest = http.request(opts, (response) => {
                callback(response);
            });
        }

        getRequest.on("error", (err) => {
            errorCallback(err);
        });
        getRequest.on('response', function (response) {
            if (response.statusCode !== 200) {
                getRequest.emit("error", response.statusMessage);
            }
            
        }); 
        getRequest.end();
    }

    private static postRequest(arg: HttpPostArguments, urlEncoded: boolean = false): Promise<any> {
        let url: string = arg.url;
        let username: string = arg.username;
        let password: string = arg.password;
        let headers: any = arg.headers;
        let inputData: any = arg.data;

        let data = "";
        if (inputData !== undefined) {
            if (inputData instanceof String || typeof inputData === "string") {
                data = inputData.toString();
            } else {
                data = Qs.stringify(inputData);
            }
        }

        let promise = new Promise<any>((resolve, reject) => {
            let opts = Http.parseUrl(url);
            opts.method = 'POST';
            opts.headers = {
                "Content-Length": data ? data.length : 0,
                "User-Agent": "Node http tool"
            };

            if (urlEncoded) {
                opts.headers = Object.assign(opts.headers, {
                    'Content-Type': 'application/x-www-form-urlencoded'
                });
            }

            if (headers !== undefined) {
                opts.headers = Object.assign(opts.headers, headers);
            }

            if (username && password) {
                opts.auth = username + ":" + password;
            }
            let callback = res => {
                let buf = "";
                res.on("data", function (d) {
                    buf += d;
                });
                res.on("end", function () {
                    resolve(buf);
                });
                res.on("error", function (e) {
                    reject(e);
                });
            };
            let postRequest: ClientRequest;
            if (opts.protocol === "https:") {
                postRequest = https.request(opts, callback);
            } else {
                postRequest = http.request(opts, callback);
            }

            postRequest.write(data);
            postRequest.end();

        });
        return promise;
    }
    
}

