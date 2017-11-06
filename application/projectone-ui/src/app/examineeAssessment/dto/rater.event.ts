import { Rater } from './rater';

export interface RaterRemovedEvent {
    removedRater: Rater;
    remainingRaters: Rater[];
}