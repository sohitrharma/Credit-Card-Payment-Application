import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthServiceService } from 'src/app/services/auth-service.service';
import { SharedService } from 'src/app/services/shared.service';
import { User } from 'src/models/user';


@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	constructor(private router: Router,private sharedService: SharedService, private authService: AuthServiceService) { }

	user:User;
	userId:number;
	ngOnInit(): void {
		if(!localStorage.getItem('currentUser')){
			this.router.navigate(['/login']);
		  }
		  this.user = this.sharedService.getCurrentCustomer();  
		  this.userId=this.user.userId;
	}

	isLoggedIn() {
		return this.authService.isLoggedIn;
	}

	// tslint:disable-next-line: typedef
	getUser() {
		console.log(this.sharedService.getCurrentCustomer())
		return this.sharedService.getCurrentCustomer();
	}

	logout(): void {
		this.authService.logout();
	}

	navCreditCards(){
		this.router.navigate(['/creditcards',this.user.userId]);
	}
	navAccounts(){
		this.router.navigate(['/accounts',this.user.userId]);
	}
}