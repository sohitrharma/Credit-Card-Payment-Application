import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';

import { User } from 'src/models/user';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  user: User = new User();
  submitted = false;

 /* newCustomer: User = {} as User;*/
  error = '';
  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  registerNewUser() {
    this.authService.addNewUser(this.user).subscribe((data: any) => {
      console.log(data)
      this.user = new User();
      this.router.navigate(['/login']);    }, 
      (    error: any) => { console.log(error); this.error = "Register Successful "});
  }

  onSubmit() {
    console.log(this.user.name);
    this.submitted = true;
    this.registerNewUser();    
    
  }

}
