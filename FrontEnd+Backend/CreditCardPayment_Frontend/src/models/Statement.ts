import { User } from "./user";

export class Statement{
    statementId?:number;
    dueAmount:number;
    billingDate:Date;
    dueDate:Date;
    user:User;
}