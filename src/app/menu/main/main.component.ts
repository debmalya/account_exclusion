import { Component, OnInit } from '@angular/core';
import { BackendService } from 'src/app/backend.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  role :string;
  constructor(private backendService:BackendService) { }

  ngOnInit(): void {
    this.role = this.backendService.role;
  }

}
