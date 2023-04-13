import { AccountType } from "src/enums/AccountType";
import { User } from "./user";

export class Account {
    accountNumber:number;
    accountType:string;
    accountName:string;
    user:User;
    balance: number;
}