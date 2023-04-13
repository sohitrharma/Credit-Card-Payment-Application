import { UserRole } from "src/enums/UserRole";
import { Address } from "src/models/Address";
import { CreditCard } from "src/models/CreditCard";
import { Statement } from "src/models/Statement";
import { Transaction } from "src/models/Transaction";


export class User {


    userId?: number;
    name: string;
    password: string;
    role: UserRole;
    dob: Date;
    email:string;
    contactNo:number;
    address:Address;
    creditcards?:CreditCard[];
    statements?:Statement[];
    accounts?:Account[];
    transactions?:Transaction[];

}