import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { PaymentService } from 'src/app/services/payment.service';
import { SharedService } from 'src/app/services/shared.service';
import { Payment } from 'src/models/Payment';
import { User } from 'src/models/user';

@Component({
  selector: 'app-showpayment',
  templateUrl: './showpayment.component.html',
  styleUrls: ['./showpayment.component.css']
})
export class ShowpaymentComponent implements OnInit {

  constructor(private paymentService: PaymentService,
    private router: Router,private sharedService:SharedService) {}

    paymentArray: any[] = [];  
    dtOptions: DataTables.Settings = {};  
    dtTrigger: Subject<any>= new Subject();  
    
    totalRecords:String;
  page :number=1;


    payments: Payment[];  
    payment : Payment=new Payment();  
    deleteMessage=false;  
    paymentlist:any;  
    isupdated = false;      
    userId:number;
    user:User;
    
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
      // this.payments=this.user.creditcards;   
      // this.paymentService.getPaymentList().subscribe(data =>{  
      // this.payments =data.response
      // this.totalRecords=data.response.length;
      // this.dtTrigger.next();  
      // })  
    }  

  }
