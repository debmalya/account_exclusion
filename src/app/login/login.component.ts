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
  userName = "";


  ngOnInit(): void {
    this.error = "";
    this.appError = "";
    this.userName = "";
  }

  login() {
    this.loginService.authenticate(this.credentials, () => {
      if (this.loginService.authenticated) {

        this.error = "";
        this.appError = "";


        if (this.loginService.role === "User") {
          this.router.navigate(["account"]);
        } else if (this.loginService.role === "Admin") {
          this.router.navigate(["approval"]);
        }

      } else {
        this.error = "";
        this.appError = this.loginService.errorMessage;
      }

    });
  }

  
}
