import { Component, OnInit } from '@angular/core';
import { BackendService } from 'src/app/backend.service';
import { Exclusionrequest } from 'src/app/exclusionrequest';

@Component({
  selector: 'app-request-view',
  templateUrl: './request-view.component.html',
  styleUrls: ['./request-view.component.css']
})
export class RequestViewComponent implements OnInit {
  exclusionRequests: Exclusionrequest[] = [];

  // properties for pagination
  thePageNumber: number = 1;
  thePageSize: number = 10;
  theTotalElements: number = 0;
  requestMap: Map<string, string> = new Map();
  submissionResult: string = "";
  role :string = "";

  constructor(private backendService:BackendService) { }

  ngOnInit(): void {
    this.backendService.getAllRequests(0, 20).subscribe(this.processResult());
    console.log("ngOnInit :" + JSON.stringify(this.exclusionRequests));
    this.role = this.backendService.role;

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

  handleAllRequestsList() {
    this.backendService.getPendingRequests(this.thePageNumber - 1, this.thePageSize).subscribe(this.processResult());
  }
}
