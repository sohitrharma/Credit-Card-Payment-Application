import { UserRole } from "src/enums/UserRole";
import { CreditCard } from "./CreditCard";
import { Statement } from "./Statement";
import { Transaction } from "./Transaction";

export class User {


    userId?: number;
    name: string;
    password: string;
    role: UserRole;
    dob: Date;
    email:string;
    contactNo:number;
    address:string;
    creditcards?:CreditCard[];
    statements?:Statement[];
    accounts?:Account[];
    userTransaction?:Transaction[];

}