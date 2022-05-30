import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrivateLayoutComponent } from './layouts/private-layout/private-layout.component';
import { AccountsComponent } from './pages/accounts/accounts.component';
import { CustomerAccountsComponent } from './pages/customer-accounts/customer-accounts.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { LoginComponent } from './pages/login/login.component';
import { NewCustomerComponent } from './pages/new-customer/new-customer.component';
import { SignupComponent } from './pages/signup/signup.component';

const routes: Routes = [
  {
    path: 'private',
    component: PrivateLayoutComponent,
    children: [
      { path: 'customers', component: CustomersComponent },
      { path: 'accounts', component: AccountsComponent },
      { path: 'new-customer', component: NewCustomerComponent },
      { path: 'customer-accounts/:id', component: CustomerAccountsComponent }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
