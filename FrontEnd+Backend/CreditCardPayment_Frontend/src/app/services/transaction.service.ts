import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = "http://localhost:8081/transaction/getall";

  constructor(private http: HttpClient) { }

  

  // getTransaction(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl}/${id}`);
  // }

  // createTransaction(employee: Object): Observable<Object> {
  //   return this.http.post(`${this.baseUrl}`, employee);
  // }

  // updateTransaction(id: number, value: any): Observable<Object> {
  //   return this.http.put(`${this.baseUrl}/${id}`, value);
  // }

  // deleteTransaction(id: number): Observable<any> {
  //   return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  // }

  getTransactionList(): Observable<any>  {

    return this.http.get(`${this.baseUrl}`);

  }
}