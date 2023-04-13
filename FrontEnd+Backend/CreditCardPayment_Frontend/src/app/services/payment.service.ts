import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payment } from 'src/models/Payment';


@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private baseUrl = 'http://localhost:8081/payment';

  constructor(private http: HttpClient) { }

  createPayment(id:number,payment: Payment): Observable<any> {
  
    return this.http.post(`${this.baseUrl}/add/${id}`, payment);
  }

  getPaymentList(): Observable<any>  {

    return this.http.get(`${this.baseUrl}/getall`);

  }
  
}
