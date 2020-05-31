import { Component, OnInit } from '@angular/core';
import { BackendService } from 'src/app/backend.service';
import { Account} from 'src/app/account';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accounts-view',
  templateUrl: './accounts-view.component.html',
  styleUrls: ['./accounts-view.component.css']
})
export class AccountsViewComponent implements OnInit {
  // properties for pagination
  thePageNumber: number = 1;
  thePageSize: number = 10;
  theTotalElements: number = 0;
  excludedAccounts: Account[] = [];
  previousAccountSearch: string = null;
  currentAccountSearch: string = null;

  constructor(private backendService: BackendService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.currentAccountSearch = params['accountNumber'];
    });
    const accountNumber: string = this.route.snapshot.paramMap.get('accountNumber');
    console.log(`ngOnInit search account containing : ${accountNumber}`);
    this.backendService.getSearchedAccount(accountNumber, 0, 20).subscribe(this.processResult());
    console.log("ngOnInit search account :" + JSON.stringify(this.excludedAccounts));
  
  }

  private processResult() {
    console.log("processResult called");
    return data => {
      this.excludedAccounts = data._embedded.excludedAccounts;
      this.thePageNumber = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalPages;
    }
  }

   handleSearchedAccountList(){
    const accountNumber: string = this.route.snapshot.paramMap.get('accountNumber');
    if (this.previousAccountSearch != accountNumber) {
      this.thePageNumber = 1;
    }
    this.backendService.getSearchedAccount(accountNumber, 0, 20).subscribe(this.processResult());
    console.log("handleSearchedAccountList search account :" + JSON.stringify(this.excludedAccounts));
    this.previousAccountSearch = accountNumber;
   }

   removeAccount(account){
     alert(JSON.stringify(account) +" will be removed from EXCLUSION_ACCOUNTS after approval. [to be implemented]");
   }
}
