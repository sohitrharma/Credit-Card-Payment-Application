import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CreditcardServiceService } from 'src/app/services/creditcard-service.service';
import { SharedService } from 'src/app/services/shared.service';
import { CreditCard } from 'src/models/CreditCard';
import { User } from 'src/models/user';

@Component({
  selector: 'app-add-creditcard',
  templateUrl: './add-creditcard.component.html',
  styleUrls: ['./add-creditcard.component.css']
})
export class AddCreditcardComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router,private creditcardservice: CreditcardServiceService,private sharedService:SharedService) { }

  creditcard: CreditCard = new CreditCard();
  creditcardsaveform: FormGroup;
  submitted = false;
  userId:number;
  user:User;



  ngOnInit() {
    if(!localStorage.getItem('currentUser')){
      this.router.navigate(['/login']);
    }
    this.user = this.sharedService.getCurrentCustomer();  
    this.userId=this.user.userId;
    this.submitted = false;
    this.creditcardsaveform = new FormGroup({
      cardName: new FormControl('', [Validators.required]),
      cardNumber: new FormControl('', [Validators.required]),
      // Validators.pattern("[4|5][0-9]{16}")
      cardType: new FormControl('', [Validators.required]),
      bankName: new FormControl('', [Validators.required]),
      cardExpiry: new FormControl('', [Validators.required]),
      cvv: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]),
    })
  }

  onSubmit() {
    this.creditcardservice.addCreditCard(this.user.userId,this.creditcardsaveform.value)
      .subscribe( data => {
       
        localStorage.setItem('currentUser',JSON.stringify(this.user));
        this.sharedService.setCurrentCustomer(this.user);
        this.router.navigate(['/creditcards',this.user.userId]);
        console.log(data);
      });
  }

  get CardName(){
    return this.creditcardsaveform.get('cardName');
  }

  
  get CardNumber(){
    return this.creditcardsaveform.get('cardNumber');
  }

  get CardType(){
    return this.creditcardsaveform.get('cardType');
  }
  get BankName(){
    return this.creditcardsaveform.get('bankName');
  }

  get ExpiryDate(){
    return this.creditcardsaveform.get('cardExpiry');
  }

  get CVV(){
    return this.creditcardsaveform.get('CVV');
  }

  addCreditCardForm(){  
    this.submitted=false;  
    this.creditcardsaveform.reset();  
  }  
}
