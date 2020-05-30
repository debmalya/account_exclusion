import { Component, OnInit } from '@angular/core';
import { BackendService } from 'src/app/backend.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private role: string;
  constructor(private backendService:BackendService,private router:Router) { }

  ngOnInit(): void {
    console.log('header ngOnInit called');
    if (this.backendService.authenticated){
      console.log('authenticated');
    }
  }

  logout() {
    this.backendService.jwtToken = "";
    this.backendService.authenticated = false;
    this.backendService.role = "";
    this.backendService.userName = "";

    console.log("logout called");
    this.router.navigate(["logout"]);
  }

}
