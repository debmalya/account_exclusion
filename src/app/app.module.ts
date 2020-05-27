import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BackendService } from './backend.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AccountListComponent } from './account-list/account-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ApprovalComponent } from './approval/approval.component';






@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AccountListComponent,
    ApprovalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [BackendService],
  bootstrap: [AppComponent]
})
export class AppModule { }
