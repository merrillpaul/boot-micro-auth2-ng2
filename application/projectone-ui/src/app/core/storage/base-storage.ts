import { AES, enc } from 'crypto-js';

const prefix = "p1storage";

export class BaseStorage {
    private static _secretKey: string = 'projectone 2017 grizzly star';
    static get(storage: Storage, key: string, encrypt: boolean = false) {
        let storageKey = BaseStorage.getStorageKey(key);
        let value = storage.getItem(storageKey);

        return !!encrypt && value !== null ?
            BaseStorage.getActualObject(AES.decrypt(value, this._secretKey).toString(enc.Utf8)) :
            BaseStorage.getActualObject(value)
    }

    static set(storage: Storage, key: string, value: any, encrypt: boolean = false): void {
        let storageKey = BaseStorage.getStorageKey(key);
        let stringifiedValue = BaseStorage.getStringifiedValue(value);
        if (!!encrypt && stringifiedValue !== null) {
            let cipherText = AES.encrypt(stringifiedValue, this._secretKey);
            storage.setItem(storageKey, cipherText.toString());
        } else {
            storage.setItem(storageKey, stringifiedValue);
        }
    }


    static remove(storage: Storage, key: string): void {
        let storageKey = BaseStorage.getStorageKey(key);
        storage.removeItem(storageKey);
    }

    static getStorageKey(key: string): string {
        return `${prefix}_${key}`;
    }

    private static getStringifiedValue(value: any): string {
        return typeof value === "string" ? value : JSON.stringify(value);
    }

    private static getActualObject(value: string): any {
        try {
            return JSON.parse(value);
        } catch (e) {
            return value;
        }
    }

}
