import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { CustomerComponent } from './components/customer/customer.component';
import { MyProfileComponent } from './components/customer/my-profile/my-profile.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import { ViewCreditcardsComponent } from './components/credit-card/view-creditcards/view-creditcards.component';
import { AddCreditcardComponent } from './components/credit-card/add-creditcard/add-creditcard.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AddAccountComponent } from './components/accounts/add-account/add-account.component';
import { ViewAccountsComponent } from './components/accounts/view-accounts/view-accounts.component';
import { BilledComponent } from './components/statements/billed/billed.component';
import { UnBilledComponent } from './components/statements/un-billed/un-billed.component';
import { HistoryComponent } from './components/transactions/history/history.component';
import { UpdateCreditcardComponent } from './components/credit-card/update-creditcard/update-creditcard.component';
import { ForgetPasswordComponent } from './components/auth/forget-password/forget-password.component';
import { ContactUsComponent } from './components/about-us/contact-us/contact-us.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ViewStatementsComponent } from './components/statements/view-statements/view-statements.component';
import { EditCustomerComponent } from './components/customer/edit-customer/edit-customer.component';
import { AuthServiceService } from './services/auth-service.service';
import { CreditcardServiceService } from './services/creditcard-service.service';
import { DataTablesModule } from 'angular-datatables';
import { NgxPaginationModule } from 'ngx-pagination';
import { SortDirective } from 'src/directive/sort.directive';
import { ShowpaymentComponent } from './components/payment/showpayment/showpayment.component';
import { AddpaymentComponent } from './components/payment/addpayment/addpayment.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    AboutUsComponent,
    CustomerComponent,
    MyProfileComponent,
    HomepageComponent,
    HeaderComponent,
    ViewCreditcardsComponent,
    AddCreditcardComponent,
    LoginComponent,
    RegisterComponent,
    AddAccountComponent,
    ViewAccountsComponent,
    BilledComponent,
    UnBilledComponent,
    HistoryComponent,
    UpdateCreditcardComponent,
    ForgetPasswordComponent,
    ContactUsComponent,
    PaymentComponent,
    ViewStatementsComponent,
    EditCustomerComponent,
    SortDirective,
    ShowpaymentComponent,
    AddpaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DataTablesModule,
    NgxPaginationModule
  

  ],
  providers: [AuthServiceService,CreditcardServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
