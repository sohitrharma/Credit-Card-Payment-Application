import { Payment } from "./Payment";
import { User } from "./user";

export class Transaction {
    tranId?:number;
    status:string;
    date:Date;
    time:Date;
    cardNo:number;
    paymentAmount:number;
    payFrom:string;
    user:User;
    payment:Payment;

}