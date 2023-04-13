import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { CreditcardServiceService } from 'src/app/services/creditcard-service.service';
import { SharedService } from 'src/app/services/shared.service';
import { CreditCard } from 'src/models/CreditCard';
import { User } from 'src/models/user';

@Component({
  selector: 'app-view-creditcards',
  templateUrl: './view-creditcards.component.html',
  styleUrls: ['./view-creditcards.component.css']
})
export class ViewCreditcardsComponent implements OnInit {

  constructor(private router: Router,private creditcardService:CreditcardServiceService,private sharedService:SharedService) { }  
  
  creditcardsArray: any[] = [];  
  dtOptions: DataTables.Settings = {};  
  dtTrigger: Subject<any>= new Subject();  
  
  creditcards:CreditCard[];  
  creditcard : CreditCard=new CreditCard();  
  deleteMessage=false;  
  editForm: FormGroup;
  creditcardlist:any;  
  isupdated = false;      
  userId:number;
  user:User;

  // totalRecords:String;
  // page :number=1;
  
  ngOnInit() {
    if(!localStorage.getItem('currentUser')){
      this.router.navigate(['/login']);
    }
    this.user = this.sharedService.getCurrentCustomer();  
    this.userId=this.user.userId;
    this.isupdated=false;  
    this.dtOptions = {  
      pageLength: 6,  
      stateSave:true,  
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],  
      processing: true  
    };
    this.creditcards =this.user.creditcards;      
    // this.creditcardService.getAllCreditCardsByUserId(this.user.userId).subscribe(data =>{  
    // this.creditcards =data.response;  
    // this.dtTrigger.next();  
    // }) 
    console.log(this.creditcards);
   
  }  

  deleteCreditCard(id: number) {  
    this.creditcardService.deleteCreditCard(id)  
      .subscribe(  
        data => {  
          console.log(data);  
          this.deleteMessage=true;  
          this.creditcardService.getAllCreditCardsByUserId(this.user.userId).subscribe(data =>{  
            this.creditcards =data.response  
            })  
        },  
        error => console.log(error));  
  }  

  updateCreditCard(id: number){  
    this.creditcardService.getCreditCardById(id)  
      .subscribe(  
        data => {  
          this.creditcardlist=data.response  ;
          this.creditcardlist=Array.of(this.creditcardlist);
          console.log(this.creditcardlist)   ;        
        },  

        error => console.log(error));  
  }  

  creditcardupdateform=new FormGroup({  
    cardId:new FormControl(),
    cardName: new FormControl('', [Validators.required]),
    cardNumber: new FormControl('', [Validators.required, Validators.min(4000000000000000), Validators.max(5999999999999999)]),
    cardType: new FormControl('', [Validators.required]),
    bankName: new FormControl('', [Validators.required]),
    cardExpiry: new FormControl('', [Validators.required]),
    cvv: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]),
  });  

  updatecc(abc) {
    this.creditcard = new CreditCard;
    this.creditcard.cardId = this.CardId.value;
    this.creditcard.cardName = this.CardName.value;
    this.creditcard.cardNumber = this.CardNumber.value;
    this.creditcard.cardType = this.CardType.value;
    this.creditcard.bankName = this.BankName.value;
    this.creditcard.cardExpiry = this.ExpiryDate.value;
    this.creditcard.cvv=this.CVV.value;
    console.log(this.CardType.value);  
     
  
   this.creditcardService.updateCreditCard(this.user.userId,this.creditcard).subscribe(  
    data => {       
      this.isupdated=true;  
      this.creditcards =this.user.creditcards;
      this.router.navigate(['/creditcards',this.userId]);  
    },  
    error => console.log(error));  
  }  

  get CardName(){
    return this.creditcardupdateform.get('cardName');
  }

  
  get CardNumber(){
    return this.creditcardupdateform.get('cardNumber');
  }

  get CardType(){
    return this.creditcardupdateform.get('cardType');
  }
  get CardId(){
    return this.creditcardupdateform.get('cardId');
  }
  get BankName(){
    return this.creditcardupdateform.get('bankName');
  }

  get ExpiryDate(){
    return this.creditcardupdateform.get('cardExpiry');
  }

  get CVV(){
    return this.creditcardupdateform.get('cvv');
  }

  changeisUpdate(){  
    this.isupdated=false;  
  }  

  addCreditCard(): void {
    this.router.navigate(['/creditcard/add',this.userId]);
  };

}
