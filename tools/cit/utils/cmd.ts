import * as childProcess from 'child_process';

export class Cmd {
    /**
    * Run a command.
    * @return a promise that will resolve the output string if successful, or
    * reject if the command's return is != 0
    */
    static run(cmd: string): Promise<any> {
        console.log("\tcmd:" + cmd);
        return new Promise((resolve, reject) => {
            let opts = {
                maxBuffer: 1024 * 1024 // increase if necessary
            };
            childProcess.exec(cmd, opts, (err, stdout, stderr) => {
                if (err) {
                    reject("cmd: " + cmd + " : " + err + " : " + stderr);
                } else {
                    resolve(stdout);
                }
            });
        });
    }

    /**
    * Run a command NOW
    * @return run the command synchronously.
    * reject if the command's return is != 0
    */
    static runNow(cmd: string): any {
        let out = { code: 0, output: "" };
        try {
            out.output = childProcess.execSync(cmd).toString().trim();
        } catch (e) {
            let msg = "runNow: " + e;
            console.log(msg);
            out.code = -1;
            out.output = e;
        }
        return out;
    }
}