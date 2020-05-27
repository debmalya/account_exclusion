import { BackendService } from '../backend.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials = { userName: '', password: '' };
  constructor(private loginService: BackendService, private router: Router) { }
  error = "";
  appError = "";
  accounts$;
  userName = "";


  ngOnInit(): void {
  }

  login() {
    this.loginService.authenticate(this.credentials, () => {
      if (this.loginService.authenticated) {

        this.error = "";
        this.appError = "";

        /*
        this.loginService.getExcludedAccounts().subscribe(
          data => {
            this.accounts$ = data;
            console.log("Accounts =" + JSON.stringify(this.accounts$));
            // this.router.navigateByUrl(`account`);
          }
        ) 
        */
        if (this.loginService.role === "User") {
          this.router.navigate(["account"]);
        }else if (this.loginService.role === "Admin"){
          this.router.navigate(["approval"]);
        }

      } else {
        console.log("Not able to login " + this.loginService.errorMessage);
        this.error = "";
        this.appError = this.loginService.errorMessage;
      }

    });
  }
}
