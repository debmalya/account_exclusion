import { Component, OnInit } from '@angular/core';
import { BackendService } from 'src/app/backend.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  role :string;
  constructor(private backendService:BackendService,private router :Router) { }

  ngOnInit(): void {
      this.role = this.backendService.role;
      console.log(`user menu ${this.role}`);
  }

  logout() {
    this.backendService.logout();
    this.router.navigate([""]);
  }

}
