import { Component, OnInit } from '@angular/core';
import { Account } from '../account'
import { BackendService } from '../backend.service';
import { FormBuilder } from '@angular/forms';
import { FormArray } from '@angular/forms';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {
  accountsForm = this.fb.group({
    accounts: this.fb.array([
      this.fb.control('')
    ])
  });
  constructor(private backendService: BackendService,private fb: FormBuilder) {
  }

  ngOnInit(): void {
    /*
    this.service.getExcludedAccounts().subscribe(
      data => {
        this.accounts = data;
      }
    )
    */
  }

  get accounts() {
    return this.accountsForm.get('accounts') as FormArray;
  }

  addAccounts() {
    this.accounts.push(this.fb.control(''));
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.accountsForm.value);
    this.backendService.addRequests(this.accountsForm.value,() => {
      console.log("Request submitted");
    })
  }

}