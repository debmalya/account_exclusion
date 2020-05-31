import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountListComponent } from './account-list/account-list.component';
import { LoginComponent } from './login/login.component';
import { ApprovalComponent } from './approval/approval.component';
import { AccountsViewComponent } from './view/accounts-view/accounts-view.component';
import { RequestViewComponent } from './view/request-view/request-view.component';



const routes: Routes = [
  { path: 'account', component: AccountListComponent },
  { path: 'approval', component: ApprovalComponent },
  { path: 'search/account/:accountNumber', component: AccountsViewComponent },
  { path: 'requests', component: RequestViewComponent },
  { path: '', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
