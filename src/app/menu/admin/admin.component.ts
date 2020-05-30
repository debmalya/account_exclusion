import { Component, OnInit } from '@angular/core';
import { BackendService } from 'src/app/backend.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  role : String
  constructor(private backendService: BackendService, private router: Router) { }

  ngOnInit(): void {
    this.role = this.backendService.role;
    console.log(`admin menu ${this.role}`);
  }

  logout() {
    this.backendService.logout();
    this.router.navigate([""]);
  }

}
