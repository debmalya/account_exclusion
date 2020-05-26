import { BackendService } from '../backend.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials = {userName: '', password: ''};
  constructor(private loginService : BackendService) { }
   error = "";
   appError = "";
   accounts$;

  ngOnInit(): void {
  }

  login() {
    this.loginService.authenticate(this.credentials,()=>{
      if (this.loginService.authenticated){
       console.log("Time to route to next page :"+JSON.stringify(this.loginService.jwtToken));
        this.error="";
        this.appError="";
//         this.accounts$ = this.loginService.getAccountList();
        // console.log("Fetched accounts :"+JSON.stringify(this.accounts$));
      }else{
        console.log("Not able to login "+this.loginService.errorMessage);
        this.error = "";
        this.appError = this.loginService.errorMessage;
      }

    });
  }
}
