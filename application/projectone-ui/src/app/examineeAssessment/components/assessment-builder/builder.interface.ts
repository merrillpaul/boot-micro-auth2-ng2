import { Output, EventEmitter } from '@angular/core';

export abstract class IBuilderComponent {
    @Output('builderValidityChange')
    validityChanges: EventEmitter<boolean> = new EventEmitter<boolean>();
}