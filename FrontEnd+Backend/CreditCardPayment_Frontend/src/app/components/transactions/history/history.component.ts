import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs';
import { SharedService } from 'src/app/services/shared.service';
import { TransactionService } from 'src/app/services/transaction.service';
import { Transaction } from 'src/models/Transaction';
import { User } from 'src/models/user';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {


  userId:number;
  user:User;
  constructor(private transactionService: TransactionService,
    private router: Router,private sharedService:SharedService) {}

    totalRecords:String;
    page :number=1;
  
    tarnsactionArray: any[] = [];  
    dtOptions: DataTables.Settings = {};  
    dtTrigger: Subject<any>= new Subject();  
    
    transactions: Transaction[];  
    transaction : Transaction=new Transaction();  
    deleteMessage=false;  
    transactionlist:any;  
    isupdated = false;      
     
    
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
      this.transactions =this.user.userTransaction;
      console.log(this.transactions);
      // this.transactionService.getTransactionList().subscribe(data =>{  
      // this.transactions =data.response;  
      // this.dtTrigger.next();  
      // })  
    }  
  
  


  // deleteEmployee(id: number) {
  //   this.transactionService.deleteTrnsaction(id)
  //     .subscribe(
  //       data => {
  //         console.log(data);
  //         this.reloadData();
  //       },
  //       error => console.log(error));
  // }

  // tarnsactionDetails(id: number){
  //   this.router.navigate(['details', id]);
  // }
}