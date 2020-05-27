import { Component, OnInit } from '@angular/core';
import { Account } from '../account'
import { BackendService } from '../backend.service';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {
  public accounts:Account[]
  constructor(private service:BackendService) { 
    this.service.getExcludedAccounts().subscribe(
      data => {
        this.accounts = data;
        console.log("Accounts =" + JSON.stringify(this.accounts));
      }
    )  
  }

  ngOnInit(): void {
  }

}
