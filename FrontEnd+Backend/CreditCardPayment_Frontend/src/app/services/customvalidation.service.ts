import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CustomvalidationService {

  
  

  accountNameValidator(accountControl: AbstractControl) {
    return new Promise(resolve => {
      setTimeout(() => {
        if (this.validateAccountName(accountControl.value)) {
          resolve({ accountNameNotAvailable: true });
        } else {
          resolve(null);
        }
      }, 1000);
    });
  }

  validateAccountName(accountName: string) {
    const AccountList = ['ankit', 'admin', 'user', 'superuser'];
    return (AccountList.indexOf(accountName) > -1);
  }
}
