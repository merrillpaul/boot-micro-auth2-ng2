import { Http } from './utils/http';
import { Logger } from './utils/logger';
import { CommandConfig, ContentPullCommand } from './commands';
import { argv } from 'optimist';
import * as readline from 'readline-sync';

class CITTools {

    static logger = Logger.getLogger(CITTools);
    static run() {

        let command = argv.command;
        try {
            let config = this.buildCommonConfig();
            switch (command) {
                case 'contentPull':
                    new ContentPullCommand().run(config);
                    break;

                default:
                    console.error("Invalid command");
                    process.exit();
            }
        } catch (e) {
            CITTools.logger.error(e);
            process.exit();
        }

    }

    private static buildCommonConfig(): CommandConfig {

        let citHost: string = process.env.CIT_HOST || 'localhost';
        let citUser: string = process.env.CIT_USER;        
        let citUrl = process.env.CIT_URL || `http://${citHost}`;
        let citPassword: string = process.env.CIT_PASSWORD;

        if (argv.ask) {
            citUrl = readline.question(`CIT Url (${citUrl}) ? `);
            citUser = readline.question(`CIT User  (${citUser || ''}) ? `);
            citPassword = readline.question(`CIT Password ? `, { hideEchoBack: true });
        }
        if (!citUser) {
            throw "CIT_USER env setting needs to be set";
        }

        if (!citPassword) {
            throw "CIT_PASSWORD env setting needs to be set";
        }
        let startedBy = process.env.BUILD_USER || 'Jenkins';


        let config: CommandConfig = {
            citHost: citHost,
            citUrl: citUrl,
            citUser: citUser,
            citPassword: citPassword,
            startedBy: startedBy,
            extraArgs: process.argv.slice(3)
        };
        return config;
    }
}

CITTools.run();