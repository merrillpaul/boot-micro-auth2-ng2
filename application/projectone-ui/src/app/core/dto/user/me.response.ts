export interface MeResponse {
    accountNonExpired:boolean;
    accountNonLocked:boolean
    authorities:string[];
    enabled:boolean;
    firstName:string;
    honorific:string;
    id:string;
    lastName: string;
    passwordExpired?:boolean;
    roles: string[];
    timezoneId: string;
    username: string;
}