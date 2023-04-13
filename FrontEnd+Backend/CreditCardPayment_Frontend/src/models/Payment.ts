import { CreditCard } from "./CreditCard";

export class Payment {
    paymentId?:number;
    status:string;
    date: Date;
    paymentAmount:number= 16932;
    creditcard?:CreditCard;
}