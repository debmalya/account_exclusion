import { Component, OnInit } from '@angular/core';
import { Account } from '../account'

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {
  private accounts:Account[]
  constructor() { }

  ngOnInit(): void {
  }

}
