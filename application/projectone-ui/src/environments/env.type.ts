export interface P1Environment {
  production: boolean;
  oauthServerBaseUrl: string;
  oauthClientId: string;
  oauthClientSecret: string;
  oauthClientScope: string;
  customerServerBaseUrl: string;
  globalServerBaseUrl: string;
  userLocale: string;
}