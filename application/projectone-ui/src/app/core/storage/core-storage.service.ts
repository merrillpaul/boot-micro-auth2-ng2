import { BaseStorage } from "./base-storage";

export class CoreStorage {


    constructor(private storage: Storage, private _encrypt: boolean = false) {
    }

    get(key: string): any {
        return BaseStorage.get(this.storage, key, this._encrypt);
    }

    set(key: string, value: any): void {
        BaseStorage.set(this.storage, key, value, this._encrypt);
    }

    remove(key: string): void {
        BaseStorage.remove(this.storage, key);
    }

    clear(): void {
        this.storage.clear();
    }
}
