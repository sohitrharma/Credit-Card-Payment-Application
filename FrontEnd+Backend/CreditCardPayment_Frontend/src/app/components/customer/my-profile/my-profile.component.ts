import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { data } from 'jquery';
import { SharedService } from 'src/app/services/shared.service';


@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  user: any = null;
  constructor( private router: Router,private sharedService: SharedService) { }

  ngOnInit(): void {
    
    if(!localStorage.getItem('currentUser')){
      this.router.navigate(['/login']);
    }
    this.user = this.sharedService.getCurrentCustomer();
    console.log(this.user)
    // if(){
    //   this.router.navigate(['/login']);
    // }
  }


}
