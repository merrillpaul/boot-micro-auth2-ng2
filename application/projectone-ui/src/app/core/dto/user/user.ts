import { MeResponse } from './me.response';

export class User {
    private _accountNonExpired:boolean;
    private _accountNonLocked:boolean
    private _authorities:string[];
    private _enabled:boolean;
    private _firstName:string;
    private _honorific:string;
    private _id:string;
    private _lastName: string;
    private _passwordExpired?:boolean;
    private _roles: string[];
    private _timezoneId: string;
    private _username: string;
    private _loggedIn: boolean = false;
    private _guestUser: boolean = true;

    constructor(
        username: string = 'guest', 
        firstName: string = 'guest',
        lastName: string = '',
        hon: string = '',
        timezoneId: string = 'America/Chicago',
        roles: string[] = ['ROLE_ANON'],
        id: string = '',
        loggedIn: boolean = false,
        accountNonExpired: boolean = true,
        accountNonLocked: boolean = true,
        enabled: boolean = true,
        passwordExpired: boolean = false,
        guestUser: boolean = true        
        ) {
        this._username = username;
        this._loggedIn = loggedIn;
        this._accountNonExpired = accountNonExpired;
        this._accountNonLocked = accountNonLocked;
        this._enabled = enabled;
        this._guestUser = guestUser;
        this._firstName = firstName;
        this._lastName = lastName;
        this._honorific = hon;
        this._id = id;
        this._roles = roles;
        this._passwordExpired = passwordExpired;
        this._timezoneId = timezoneId;
    }

    static createUser(data: MeResponse): User {
        return new User(
            data.username,
            data.firstName,
            data.lastName,
            data.honorific,
            data.timezoneId,
            data.roles,
            data.id,
            true,
            data.accountNonExpired,
            data.accountNonLocked,
            data.enabled,
            data.passwordExpired,
            false
        );
    }

    public get accountNonExpired(): boolean {
        return this._accountNonExpired;
    }
    
    public get accountNonLocked(): boolean {
        return this._accountNonLocked;
    }

    public get authorities(): string[] {
        return this._authorities;
    }

    public get username(): string {
        return this._username;
    }

    public get firstName() : string {
        return this._firstName;
    }

    public get lastName() : string {
        return this._lastName;
    }


    public get honorific(): string {
        return this._honorific;
    }

    public get roles(): string [] {
        return this._roles;
    }

    public get timezoneId(): string {
        return this._timezoneId;
    }

    public get enabled(): boolean {
        return this._enabled;
    }

    public get loggedIn(): boolean {
        return this._loggedIn;
    }

    public get guestUser(): boolean {
        return this._guestUser;
    }

    public get passwordExpired(): boolean {
        return this._passwordExpired;
    }

    public get id(): string {
         return this._id;
    }
}
