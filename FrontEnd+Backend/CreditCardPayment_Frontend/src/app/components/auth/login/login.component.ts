import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { SharedService } from 'src/app/services/shared.service';
import { User } from 'src/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  currentUser: any;
  error = '';
  redirectTo = false;
  submitted = false;
  constructor(
    private authService: AuthServiceService, private sharedService: SharedService, private router: Router, private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const params = this.route.snapshot.queryParamMap;
    if (params.keys.length > 0) {
      if (params.get('redirectTo')) {
        this.redirectTo = true;
      }
    }
  }
  
  /*validate() {
    const resp: any = await this.authService.validateUser(this.user);
    if (resp.status) {
      this.error = "Invalid Username/Password";
    }
    else {
      this.sharedService.setCurrentCustomer(this.authService.getCurrentCustomer());
     // this.redirectTo ? this.router.navigate(['/home']) : this.router.navigate(['/login']);
     this.router.navigate(['/home']);
    }
  }*/

   validate() {
   this.authService.validateUser(this.user).subscribe((data: any) => {
      console.log(data)
      this.user = data.response;
      localStorage.setItem('currentUser',JSON.stringify(this.user));
      this.sharedService.setCurrentCustomer(this.user);
      this.router.navigate(['/home']);
    }, 
      (error: any) => { console.log(error); this.error = "Invalid username/password "});
  }

  onSubmit() {
    console.log(this.user);
    this.submitted = true;
    this.validate();    
    
  }


}
