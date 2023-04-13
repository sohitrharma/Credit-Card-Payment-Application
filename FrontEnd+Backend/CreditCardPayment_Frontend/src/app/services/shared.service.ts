import { Injectable } from '@angular/core';
import { CreditCard } from 'src/models/CreditCard';
import { User } from 'src/models/user';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  currentCustomer: User = new User();
  currentcreditcard:CreditCard=new CreditCard();
  constructor() { }

  setCurrentCustomer(customer: User): void {
    this.currentCustomer = customer;
  }
  getCurrentCustomer(): User {
    return this.currentCustomer;
  }
  setCurrentCreditcard(creditcard:CreditCard): void {
    this.currentcreditcard = creditcard;
  }
  getCurrentCreditcard(): CreditCard {
    return this.currentcreditcard;
  }
}

