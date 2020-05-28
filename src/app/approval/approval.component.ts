import { Component, OnInit } from '@angular/core';
import { Exclusionrequest } from '../exclusionrequest';
import { BackendService } from '../backend.service';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements OnInit {

  exclusionRequests: Exclusionrequest[];
  constructor(private service: BackendService) { }

  ngOnInit(): void {
    this.service.getPendingRequests().subscribe(
      data => {
        this.exclusionRequests = data;
      }
    )
  }


}


