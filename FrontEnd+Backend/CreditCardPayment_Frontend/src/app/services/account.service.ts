import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseUrl = 'http://localhost:8081/Account';

  constructor(private http: HttpClient) { }

  getAccount(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/accountNumber/${id}`);
  }

  createAccount(id:number,account: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add/${id}`, account);
  }

  deleteAccount(accountNumber: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${accountNumber}`, { responseType: 'text' });
  }

  getAccountList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getall`);
  }

}