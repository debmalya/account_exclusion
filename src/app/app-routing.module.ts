import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountListComponent } from './account-list/account-list.component';
import { LoginComponent } from './login/login.component';
import { ApprovalComponent } from './approval/approval.component';



const routes: Routes = [
  { path: 'account', component: AccountListComponent },
  { path: 'approval', component: ApprovalComponent },
  // { path: 'login', component: LoginComponent },
  // { path: 'logout', component: LoginComponent },
  { path: '', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
