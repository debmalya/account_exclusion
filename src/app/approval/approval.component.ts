import { Component, OnInit } from '@angular/core';
import { Exclusionrequest } from '../exclusionrequest';
import { BackendService } from '../backend.service';



@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements OnInit {

  exclusionRequests: Exclusionrequest[] = [];

  // properties for pagination
  thePageNumber: number = 1;
  thePageSize: number = 10;
  theTotalElements: number = 0;
  requestMap: Map<string, string> = new Map();
  submissionResult: string = "";


  constructor(private backendService: BackendService) { }

  ngOnInit(): void {

    this.backendService.getPendingRequests(0, 20).subscribe(this.processResult());
    console.log("ngOnInit :" + JSON.stringify(this.exclusionRequests));
  }

  handlePendingRequestList() {
    this.backendService.getPendingRequests(this.thePageNumber - 1, this.thePageSize).subscribe(this.processResult());
  }

  private processResult() {
    console.log("processResult called");
    return data => {
      this.exclusionRequests = data._embedded.requests;
      this.thePageNumber = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalPages;
    }
  }

  addToApproval(exclusionrequest, action) {
    console.log("addToApproval called");
    var submitAction = "A";
    if (action === "REJECT") {
      submitAction = "R";
    }
    this.requestMap.set(exclusionrequest.requestID, submitAction);
    console.log("addToApproval request map modified" + JSON.stringify(this.requestMap));
  }

  submitForApproval() {
    for (let entry of this.requestMap.entries()) {
      var requests: string[] = [];
      console.log(`{"requestID": "${entry[0]}","action":"${entry[1]}"}`);
      requests.push(`{"requestID": "${entry[0]}","action":"${entry[1]}"}`);
    }
    console.log(`Final payload :${requests}`);
    this.backendService.approval("["+requests+"]", () => {
      console.log("Approval request submitted");
      this.submissionResult = "Approval request submitted";
      this.requestMap.clear();
    });
  }

}


