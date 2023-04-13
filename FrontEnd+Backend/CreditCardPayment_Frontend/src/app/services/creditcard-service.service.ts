import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreditcardServiceService {

  private baseUrl = 'http://localhost:8081/creditcard';
  constructor(private http: HttpClient) { }


  getAllCreditCards(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getall`);
  }
  
  getCreditCardById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/get/id/${id}`);
  }

  addCreditCard(id: number,creditcard: object): Observable<object> {  
    return this.http.post(`${this.baseUrl}/add/${id}`, creditcard);  
  }  
  
  deleteCreditCard(id: number): Observable<any> {  
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });  
  }  

  updateCreditCard(id: number, value: any): Observable<Object> {  
    return this.http.put(`${this.baseUrl}/update/${id}`, value);  
  }  
  getAllCreditCardsByUserId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getall//userId/${id}`);
  }
}
