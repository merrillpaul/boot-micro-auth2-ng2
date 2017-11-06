import { CommandConfig } from './config';

export interface ICommand {
    run(config: CommandConfig): void;
}