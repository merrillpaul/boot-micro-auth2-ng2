import { Injectable, EventEmitter } from "@angular/core";



@Injectable()
export class NavigationEmitter {
    constructor() {
    }

    public showNav: EventEmitter<boolean> = new EventEmitter();
    public sessionValid: EventEmitter<boolean> = new EventEmitter();
    public userImageUrl: EventEmitter<string> = new EventEmitter();


}
