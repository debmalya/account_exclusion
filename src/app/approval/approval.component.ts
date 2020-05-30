import { Component, OnInit } from '@angular/core';
import { Exclusionrequest } from '../exclusionrequest';
import { BackendService } from '../backend.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements OnInit {

  exclusionRequests: Exclusionrequest[];
  constructor(private service: BackendService,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.service.getPendingRequests(0,10).subscribe(
      data => {
        this.exclusionRequests = data;
        console.log(JSON.stringify(this.exclusionRequests));
      }
    )
  }


}


