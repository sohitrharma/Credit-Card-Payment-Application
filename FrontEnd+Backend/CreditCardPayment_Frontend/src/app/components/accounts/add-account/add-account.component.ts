import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import { SharedService } from 'src/app/services/shared.service';
import { User } from 'src/models/user';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {

  submitted = false;
  accountsaveForm:FormGroup;
  userId:number;
  user:User;


  constructor(private fb: FormBuilder , private accountService: AccountService ,private router: Router,private sharedService:SharedService) { }

  ngOnInit() {
    if(!localStorage.getItem('currentUser')){
      this.router.navigate(['/login']);
    }
    this.user = this.sharedService.getCurrentCustomer();  
    this.userId=this.user.userId;
    this.submitted = false;
    this.accountsaveForm = new FormGroup({
      accountName: new FormControl('', [Validators.required,Validators.minLength(4)]),
      accountNumber: new FormControl('', [Validators.required, Validators.min(100000000000), Validators.max(999999999999)]),
      accountType: new FormControl('', [Validators.required]),
      balance: new FormControl('', [Validators.required, Validators.minLength(3)]),
    })
  }

  onSubmit() {
    this.accountService.createAccount(this.userId,this.accountsaveForm.value)
    .subscribe(data=>{
      this.router.navigate(['/accounts',this.userId]);
    });    
  }
  get AccountName(){
    return this.accountsaveForm.get('accountName');
  }
  get AccountNumber(){
    return this.accountsaveForm.get('accountNumber');
  }
  get AccountType(){
    return this.accountsaveForm.get('accountType');
  }
  get Balance(){
    return this.accountsaveForm.get('balance');
  }
  addAccountForm(){
    this.submitted=false;
    this.accountsaveForm.reset();
  }
}