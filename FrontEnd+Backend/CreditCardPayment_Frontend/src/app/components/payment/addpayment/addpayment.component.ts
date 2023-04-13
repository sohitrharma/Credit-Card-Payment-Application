import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from 'src/app/services/payment.service';
import { CreditCard } from 'src/models/CreditCard';
import { Payment } from 'src/models/Payment';

@Component({
  selector: 'app-addpayment',
  templateUrl: './addpayment.component.html',
  styleUrls: ['./addpayment.component.css']
})
export class AddpaymentComponent implements OnInit {

  url:string=window.location.search;
  payment: Payment = new Payment();
  submitted = false;
  creditcard:CreditCard;
  id:string;
  cardId:number;
  

  constructor(private payemntService: PaymentService,
    private router: Router,private activatedRoute: ActivatedRoute) { this.activatedRoute.queryParams.subscribe(params => {
      let date = params['startdate'];
      console.log(date); // Print the parameter to the console. 
  }); }

  ngOnInit() {
    this.id=this.activatedRoute.snapshot.paramMap.get("id");
    this.cardId=+this.id;
    console.log('url');
  }
  

  newpayment(): void {
    this.submitted = false;
    this.payment = new Payment();
  }

  save() {
    this.payemntService.createPayment(this.cardId,this.payment).subscribe(data => {
      console.log(data)
      this.payment = new Payment();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    console.log(this.payment.paymentId);
    this.submitted = true;
    this.save();    
    
  }

  gotoList() {
    this.router.navigate(['/payments']);
  }

}
