import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import { SharedService } from 'src/app/services/shared.service';
import { User } from 'src/models/user';

@Component({
  selector: 'app-view-accounts',
  templateUrl: './view-accounts.component.html',
  styleUrls: ['./view-accounts.component.css']
})
export class ViewAccountsComponent implements OnInit {
  totalRecords: String;
  page: number = 1;
  user: User;
  userId: number;


  accounts: Account[];

  constructor(private accountService: AccountService,
    private router: Router, private sharedService: SharedService) { }

  ngOnInit() {
    if (!localStorage.getItem('currentUser')) {
      this.router.navigate(['/login']);
    }
    this.user = this.sharedService.getCurrentCustomer();
    this.userId = this.user.userId;
    // this.reloadData();
    this.accounts = this.user.accounts;
  }

  // reloadData() {
    
  // }

  deleteAccount(accountNumber: number) {
    this.accountService.deleteAccount(accountNumber)
      .subscribe(
        data => {
          console.log(data);
          // this.reloadData();
        },
        error => console.log(error));
  }
  addAccount(): void {
    this.router.navigate(['/account/add',this.userId]);
  };
}
