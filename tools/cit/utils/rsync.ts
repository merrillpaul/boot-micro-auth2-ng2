import { Cmd } from './cmd';

export class RSync {
    /**
    * Rsync from one directory to another.
    * @return es6 promise
    */
    static sync(fromDir: string, toDir: string, opts: string): Promise<any> {
        return Cmd.run(RSync.prepareRsyncCommand(fromDir, toDir, opts));
    }

    /**
    * Rsync from one directory to another.
    */
    static syncNow(fromDir: string, toDir: string, opts: string): any {
        return Cmd.runNow(RSync.prepareRsyncCommand(fromDir, toDir, opts));
    }

    private static prepareRsyncCommand(fromDir: string, toDir: string, opts: string): string {
        return `rsync ${opts} ${fromDir} ${toDir}`;
    }
}