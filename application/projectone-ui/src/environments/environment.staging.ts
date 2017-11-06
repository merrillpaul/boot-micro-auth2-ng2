/**
 * This is staging environment variables file.
 *
 */
import { P1Environment } from './env.type';
export const environment: P1Environment = {
    production: false,
    oauthServerBaseUrl: 'https://35.161.159.36:8443/auth',
    oauthClientId: 'projectOneApp',
    oauthClientSecret: '$2y$10$qNoD/wRvfGu7Ta8PbrqEiOeXxGrcXzaTAEN9zRqMXqXYwVhSWKWRW',
    oauthClientScope: 'read',
    customerServerBaseUrl: 'https://52.41.252.212:8443/customer',
    userLocale: 'en-US',
    globalServerBaseUrl: 'https://52.42.8.158:8443/global'
};
