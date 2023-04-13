import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { SharedService } from 'src/app/services/shared.service';
import { User } from 'src/models/user';


@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {

  user: User = new User();
  error = "";
  constructor(private sharedService: SharedService, private authService: AuthServiceService, private router: Router) { }

  ngOnInit(): void {
    if(!localStorage.getItem('currentUser')){
      this.router.navigate(['/login']);
    }
    this.user = this.sharedService.getCurrentCustomer();
  }

  updateUser(): any {
    this.authService.updateCurrentUserDetails(this.user);
    //custForm.reset();
    //this.form.reset();
    this.error="Your profile updated"
    this.router.navigate(['/edit-customer']);
  }

  /*user: User = new User();
  submitted = false;
  error = '';
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  updateUser() {
    this.authService.updateCurrentUserDetails(this.user).subscribe((data: any) => {
      console.log(data)
      this.user = new User();
      this.router.navigate(['/home']);
      }, 
      (    error: any) => { console.log(error);});
  }

  onSubmit() {
    console.log(this.user.name);
    this.submitted = true;
    this.updateUser();    
    
  }*/

}
