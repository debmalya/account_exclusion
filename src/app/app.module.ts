import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BackendService } from './backend.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AccountListComponent } from './account-list/account-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ApprovalComponent } from './approval/approval.component';
import { HeaderComponent } from './menu/header/header.component';
import { FooterComponent } from './menu/footer/footer.component';
import { MainComponent } from './menu/main/main.component';
import { UserComponent } from './menu/user/user.component';
import { AdminComponent } from './menu/admin/admin.component';
import { AccountsViewComponent } from './view/accounts-view/accounts-view.component';
import { RequestViewComponent } from './view/request-view/request-view.component';






@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AccountListComponent,
    ApprovalComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    UserComponent,
    AdminComponent,
    AccountsViewComponent,
    RequestViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers: [BackendService],
  bootstrap: [AppComponent]
})
export class AppModule { }
