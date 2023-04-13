import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactUsComponent } from './components/about-us/contact-us/contact-us.component';
import { AddAccountComponent } from './components/accounts/add-account/add-account.component';
import { ViewAccountsComponent } from './components/accounts/view-accounts/view-accounts.component';
import { ForgetPasswordComponent } from './components/auth/forget-password/forget-password.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AddCreditcardComponent } from './components/credit-card/add-creditcard/add-creditcard.component';
import { ViewCreditcardsComponent } from './components/credit-card/view-creditcards/view-creditcards.component';
import { EditCustomerComponent } from './components/customer/edit-customer/edit-customer.component';
import { MyProfileComponent } from './components/customer/my-profile/my-profile.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { AddpaymentComponent } from './components/payment/addpayment/addpayment.component';
import { PaymentComponent } from './components/payment/payment.component';
import { HistoryComponent } from './components/transactions/history/history.component';

const routes: Routes = [
  {​​​​​​​​ path: '', redirectTo: '/login', pathMatch: 'full' }​​​​​​​​,
  {​​​​​​​​ path: 'home', component: HomepageComponent }​​​​​​​​,
  {​​​​​​​​ path: 'login', component: LoginComponent}​​​​​​​​,
  {​​​​​​​​ path: 'register', component: RegisterComponent, pathMatch: 'full' }​​​​​​​​,​​​​​​
  {​​​​​​​​ path: 'forget-password', component: ForgetPasswordComponent}​​​​​​​​,
  {​​​​​​​​ path: 'contact-us', component: ContactUsComponent}​​​​​​​​,
  {​​​​​​​​ path: 'creditcards/:id', component: ViewCreditcardsComponent}​​​​​​​​,
  {​​​​​​​​ path: 'accounts/:id', component: ViewAccountsComponent}​​​​​​​​,
  {​​​​​​​​ path: 'transactions/:id', component: HistoryComponent}​​​​​​​​,
  {​​​​​​​​ path: 'my-profile', component: MyProfileComponent}​​​​​​​​,
  { path: 'edit-customer', component: EditCustomerComponent},
  {​​​​​​​​ path: 'creditcard/add/:id', component: AddCreditcardComponent}​​​​​​​​,
  {​​​​​​​​ path: 'account/add/:id', component: AddAccountComponent}​​​​​​​​,
  {​​​​​​​​ path: 'payment/add/:id', component: AddpaymentComponent}​​​​​​​​,
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
