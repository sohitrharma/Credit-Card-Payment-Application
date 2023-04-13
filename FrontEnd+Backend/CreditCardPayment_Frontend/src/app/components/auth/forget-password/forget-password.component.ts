import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { SharedService } from 'src/app/services/shared.service';
import { User } from 'src/models/user';
;


@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
  user: User = new User();
  submitted = false;
  constructor(private sharedService: SharedService, private authService: AuthServiceService, private router: Router) { }

  ngOnInit(): void {
   // this.user = this.sharedService.getCurrentCustomer();
  }

  changePassword(): any {
    this.authService.updatePasswordDetails(this.user);
    this.router.navigate(['/login']);
  }   
   }
  /*async chnagePassword() {
    const resp: any = await this.authService.updateCurrentUserDetails(this.user);
    if (resp.status) {
      this.error = "Invalid Username";
    }
    else {
      this.sharedService.setCurrentCustomer(this.authService.getCurrentCustomer());
     // this.redirectTo ? this.router.navigate(['/home']) : this.router.navigate(['/login']);
     this.router.navigate(['/login']);
    }
  }*/


