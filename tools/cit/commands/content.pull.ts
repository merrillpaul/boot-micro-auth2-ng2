import { ICommand } from './icommand';
import { CommandConfig } from './config';
import { Logger } from '../utils/logger';
import { Http } from '../utils/http';
import { TarUtils } from '../utils/tar';
import * as Fs from 'fs';
import { IncomingMessage } from 'http';
import * as tmp from 'tmp';
import * as rimraf from 'rimraf';
import { argv } from 'optimist';
import * as readline from 'readline-sync';

/**
 * An example CLI invocation:
 * `META_DEST=/Volumes/projectrepo/projectone/ProjectOne/tools/development/data/cit  \
 *   CIT_URL=http://10.32.14.185:9090/choose \
 *   CIT_USER=contentPull CIT_PASSWORD=xxxxxxx \
 *  npm run contentPull`
 *
 * or
 * npm run contentPullAsk
 *
 */
export class ContentPullCommand implements ICommand {

    private logger: Logger = Logger.getLogger(ContentPullCommand);
    private config: CommandConfig;

    run(config: CommandConfig): void {
        this.config = config;
        let metaDataDestDir = process.env.META_DEST;
        if (argv.ask) {
            metaDataDestDir = readline.question(`Content download folder (${metaDataDestDir}) ? `);
        }
        config.metadataDestDir = metaDataDestDir;

        Promise.resolve(true)
            .then(this.downloadMetadata.bind(this))
            .then((res) => this.logger.success("Content pull done", res))
            .catch(error => {
                this.logger.error("Error in Content pull", error);
                process.exit(1);
            });
    }

    downloadMetadata(): Promise<any> {

        let p = new Promise<any>((resolve, reject) => {
            let tmpDir = tmp.dirSync();
            this.logger.info('creating temp directory for zip copy in ', tmpDir.name);
            this.logger.info("downloading metadata file from  ", this.config.citUrl);
            let file = Fs.createWriteStream(`${tmpDir.name}/metadata.zip`);
            let size = 0
            Http.getRaw({
                url: this.config.citUrl + "/contentTool/allTestMetaDataZipDownload",
                username: this.config.citUser,
                password: this.config.citPassword
            }, (resp: IncomingMessage) => {
                resp.on('data', (data) => {
                    file.write(data);
                }).on('end', () => {
                    file.end();
                    this.logger.success(`saved metadata file in ${tmpDir.name}/metadata.zip`);
                    TarUtils.unzipNow(`${tmpDir.name}/metadata.zip`, this.config.metadataDestDir)
                    resolve('done');
                    rimraf(tmpDir.name, () => this.logger.info("Removed tmp file"));
                });

            }, (err) => {
                reject({ msg: 'Error in downloading metadata', error: err });
            });
        });

        return p;
    }

}
