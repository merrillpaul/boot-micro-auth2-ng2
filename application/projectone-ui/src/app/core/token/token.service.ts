import { EventEmitter, Injectable } from "@angular/core";
import { CoreStorage } from "../storage/core-storage.service";
import { OAuthResponse } from "../oauth/oauth-response";
import { CoreEventService } from '../services/core.event.service';
/**
 *
 * TokenService class manages user oauth token.
 */
@Injectable()
export class TokenService extends CoreStorage {

    tokenValidityEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor() {
        super(localStorage, true);
    }

    getTokenValidityEmitter() {
        return this.tokenValidityEmitter;
    }

    getAccessToken(): string {
        return this.get("token");
    }

    setAccessToken(token: string): void {
        this.set("token", token);

    }

    getRefreshToken(): string {
        return this.get("refresh_token");
    }

    setRefreshToken(token: string): void {
        this.set("refresh_token", token);
    }

    setExpiryTime(time: number) {
        this.set("expires_At", time + "")
    }

    getExpiryTime() {
        return this.get("expires_At");
    }


    isTokenExpired(): boolean {
        let expiresAtTime: number = parseInt(this.getExpiryTime());
        if (isNaN(expiresAtTime)) { // some savvy user manipulated the localstorage time
            return true;
        }
        let now = new Date();
        if (now.getTime() < expiresAtTime) {
            return false;
        }
        return true;
    }

    isTokenAvailable() {
        let local_token = this.getAccessToken();
        return (!!local_token) ? true : false;
    }

    clearTokenData() {
        this.remove("expires_At");
        this.remove("token");
        this.remove("refresh_token");
        this.remove("expires_in");
        this.tokenValidityEmitter.emit(false);
        console.log("Token Removed:" + this.remove("token"));
    }

    hasValidToken(): boolean {
        return this.isTokenAvailable() && (!this.isTokenExpired());
    }

    setProfileName(profileName: string) {
        this.set("profile_name", profileName);
    }

    getProfileName(): string {
        return this.get("profile_name");
    }

    saveTokenData(obj: any) {
        let oauthResponse: OAuthResponse = new OAuthResponse(obj);
        this.setAccessToken(oauthResponse.getAccessToken());
        this.setRefreshToken(oauthResponse.getRefreshToken());
        this.setExpiryTime(this.createExpiresAtDate(oauthResponse.getExpiresIn()));

        // Emit event
        this.tokenValidityEmitter.emit(this.hasValidToken());
    }

    createExpiresAtDate(expiresInSeconds: number): number {
        if (!!expiresInSeconds) {
            let now = new Date();
            let expiresAtTime = now.getTime() + (expiresInSeconds * 1000);
            return expiresAtTime;
        }
        return 0;
    }
}
