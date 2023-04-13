import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/models/user';
import { SharedService } from './shared.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private baseUrlRegister = 'http://localhost:8081/user/add';
  private baseUrlLogin ='http://localhost:8081/user/login';
  private baseUrlUpdateUser = 'http://localhost:8081/user/update';
  private baseUrlPassword ='http://localhost:8081/user/resetPassword';

  isLoggedIn = false;
  user: User = new User();
  currentUser: any;
  constructor(private httpClient: HttpClient, private router: Router,private sharedService: SharedService) { }

  async logout(): Promise<void> {
    this.isLoggedIn = false;
    localStorage.removeItem('currentUser');
		this.currentUser = null;
    await this.router.navigate(['/login']);
    localStorage.clear();
    location.reload(true);
  }

  /*async validateUser(user: any): Promise<any> {
    let resp = {};
    /*if (user.userName === 'admin') {
      if (user.password === 'admin123') {
        this.isLoggedIn = true;
        this.role = 'Admin';
        resp = {
          status: true,
        };
      } else {
        resp = {
          status: false,
          error: 'Incorrect username/password.'
        };
      }
    }
    else {
      await this.httpClient.post(`${this.baseUrlLogin}`, user).toPromise().then((response: any) => {
        this.customer = response;
        this.isLoggedIn = true;
        resp = {
          status: true
        };
      }).catch((error: HttpErrorResponse) => {
        resp = {
          status: false,
          error: error.error.message,
        };
      });
    //}
    return resp; 
  }*/

  validateUser(user : User): Observable<any>{
    console.log(user.name);
    this.isLoggedIn = true;
//this.currentUser=this.httpClient.post(`${this.baseUrlLogin}`, user);
    //localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
    return this.httpClient.post(`${this.baseUrlLogin}`, user);
  }

  addNewUser(user: User): Observable<any> {
      console.log(user.name);
      return this.httpClient.post(`${this.baseUrlRegister}`, user);
  }

  // getCurrentCustomer(): User {
  //  window.localStorage.getItem('currentUser');
  //  console.log(this.currentUser)
  //   return this.currentUser;
  // }

  async updateCurrentUserDetails(user: any): Promise<any> {
    let resp = {};
    this.httpClient.put(`${this.baseUrlUpdateUser}`, user).toPromise().then((response: any) => {
      resp = {
        status: true
      };
      console.log(response)
      this.user = response.response;
      localStorage.setItem('currentUser',JSON.stringify(this.user));
      this.sharedService.setCurrentCustomer(this.user);
    }).catch((error: HttpErrorResponse) => {
      resp = {
        status: false,
        error: error.error.message,
      };
    });
    return resp;
  }

  async updatePasswordDetails(user: any): Promise<any> {
    let resp = {};
    this.httpClient.put(`${this.baseUrlPassword}`, user).toPromise().then((response: any) => {
      resp = {
        status: true
      };
    }).catch((error: HttpErrorResponse) => {
      resp = {
        status: false,
        error: error.error.message,
      };
    });
    return resp;
  }
  /*updateCurrentUserDetails(user: User): Observable<any> {
    console.log(user.name);
    return this.httpClient.post(`${this.baseUrlUpdateUser}`, user);
}*/
}
