import { CreditCardType } from "src/enums/CreditCardType";
import { Payment } from "./Payment";
import { User } from "./user";

export class CreditCard {


    cardId?: number;
    cardName: string;
    cardNumber: number;
    cardType: string;
    cardExpiry: Date;
    bankName:string;
    cvv:number;
    user?: User; 
    payment?: Payment[];
}