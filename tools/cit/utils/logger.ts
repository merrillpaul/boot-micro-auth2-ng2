const OK = "\x1b[32m";
const NOK = "\x1b[31m";
const WARN = "\x1b[43m";
const RESET = "\x1b[0m";
const INFO = "\x1b[34m";

interface WithName {
    name: string;
}

export class Logger {

    private constructor(private src: WithName | string) {

    }

    info(msg?: string, ...data: any[]): void {
        let args = this.buildLogArray(INFO, msg).concat(data).concat(RESET);
        console.info.apply(console, args);
    }

    error(msg?: string, ...data: any[]): void {
        let args = this.buildLogArray(NOK, msg).concat(data).concat(RESET);
        console.error.apply(console, args);
    }

    warn(msg?: string, ...data: any[]): void {
        let args = this.buildLogArray(WARN, msg).concat(data).concat(RESET);
        console.error.apply(console, args);
    }

    debug(msg?: string, ...data: any[]): void {
        let args = this.buildLogArray(RESET, msg).concat(data).concat(RESET);
        console.error.apply(console, args);
    }

    success(msg?: string, ...data: any[]): void {
        let args = this.buildLogArray(OK, msg).concat(data).concat(RESET);
        console.error.apply(console, args);
    }

    private buildLogArray(color: string, msg?: string): any[] {
        let name;
        if (this.src instanceof String || typeof this.src === "string") {
            name = this.src;
        } else {
            name = this.src.name;
        }
        return [
            color,
            "[ ",
            name,
            " ] >>>> ",
            msg
        ];
    }

    /**
     * allows to get a logger with a string or class 
     * let logger = Logger.getLogger(AClass);
     * or
     * let logger = Logger.getLogger("AString");    
     * @param src a string or class
     */
    static getLogger(src: WithName | string): Logger {
        return new Logger(src);
    }

}


