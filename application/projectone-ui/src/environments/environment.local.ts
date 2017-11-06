
/**
 * This is default(dev) environment variables file.
 * For deploying into a local https like apache or nginx
 */
import { P1Environment } from './env.type';
export const environment: P1Environment = {
    production: false,
    oauthServerBaseUrl: `${window.location.origin}:9999/auth`,
    oauthClientId: 'projectOneApp',
    oauthClientSecret: 'password1',
    oauthClientScope: 'read',
    customerServerBaseUrl: `${window.location.origin}:9998/customer`,
    userLocale: 'en-US',
    globalServerBaseUrl: `${window.location.origin}:9997/global`
};
