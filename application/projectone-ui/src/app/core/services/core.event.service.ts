import { EventEmitter, Injectable } from '@angular/core';

@Injectable()
export class CoreEventService {

    constructor() { }

    private static _timeoutEvent: EventEmitter<void> = new EventEmitter<void>();

    public get timedout(): EventEmitter<void> {
        return CoreEventService._timeoutEvent;
    }

    public fireTimedout(): void {
        CoreEventService._timeoutEvent.emit();
    }
}