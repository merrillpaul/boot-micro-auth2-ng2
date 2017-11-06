import { Gender } from "./gender";
export class Examinee {
    public id: string;
    public firstName: string;
    public lastName: string;
    public middleName: string;
    public gender: Gender;
    public dob: Date;
    public examineeId: string;
    public comments: string;
    public version: number;

    constructor(id?: string, firstName?: string, lastName?: string, middleName?: string, gender?: Gender, dob?: Date, examineeId?: string, version?: number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.dob = dob;
        this.version = version;
    }

    public setId(id: string) {
        this.id = id;
    }

    public getId() {
        return this.id;
    }

    public setFirstName(fname) {
        this.firstName = fname;
    }

    public getFirstName() {
        return this.firstName;
    }

    public setLastName(lname) {
        this.lastName = lname;
    }

    public getLastName() {
        return this.lastName;
    }

    public setMiddleName(middleName) {
        this.middleName = middleName;
    }

    public getMiddleName() {
        return this.middleName;
    }

    public setGender(gender) {
        this.gender = gender;
    }

    public getGender() {
        return this.gender;
    }

    public setDob(dob: Date) {
        this.dob = dob;
    }

    public getDob(): Date {
        return this.dob;
    }

    public getExamineeId() {
        return this.examineeId;
    }

    public setExamineeId(value: string) {
        this.examineeId = value;
    }
    public getVersion() {
        return this.version;
    }

    public setVersion(value: number) {
        this.version = value;
    }

}
