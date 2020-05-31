import { Component, OnInit } from '@angular/core';
import { BackendService } from '../backend.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-batchrun',
  templateUrl: './batchrun.component.html',
  styleUrls: ['./batchrun.component.css']
})
export class BatchrunComponent implements OnInit {

  constructor(private backendService:BackendService,private router:Router) { }

  ngOnInit(): void {
    console.log("ngOnInit before calling batch run");
    this.backendService.batchRun(()=>{
      console.log("batch run executed");
    });
  }

  callBatch(){
    this.backendService.batchRun(()=>{
      console.log("batch run executed");
    });
  }
}
