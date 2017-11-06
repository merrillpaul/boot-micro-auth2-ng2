// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

/**
 * This is default(dev) environment variables file.
 *
 */
import { P1Environment } from './env.type';
export const environment: P1Environment = {
    production: false,
    oauthServerBaseUrl: 'http://localhost:9999/auth',
    oauthClientId: 'projectOneApp',
    oauthClientSecret: 'password1',
    oauthClientScope: 'read',
    customerServerBaseUrl: 'http://localhost:9998/customer',
    userLocale: 'en-US',
    globalServerBaseUrl: 'http://localhost:9997/global'
};
