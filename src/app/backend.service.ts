import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';
import { Account } from './account';
import { Exclusionrequest } from './exclusionrequest';



@Injectable({
  providedIn: 'root'
})
export class BackendService {
  authenticated = false;
  jwtToken = "";
  errorMessage = "";
  role = "";

  constructor(private httpClient: HttpClient) { }

  private validationURL = '/authenticate';
  private accountListURL = '/api/account/v0/retrieveAll';
  private exclusionRequests = '/api/approval/v0/get/all';

  // accounts: Account[];

  // to authenticate a user 
  authenticate(credentials, callback) {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    this.httpClient.post<string>(this.validationURL, credentials, httpOptions).subscribe(response => {
      if (response['jwtToken']) {
        this.authenticated = true;
        this.jwtToken = response['jwtToken'];
        this.role = response['role'];

      } else {
        if (response['errorMessage']) {
          this.errorMessage = response['errorMessage'];
        }
        this.authenticated = false;
      }
      return callback && callback();
    });
  }

  // to retrieve already excluded account list
  getExcludedAccounts(): Observable<Account[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.jwtToken}`
      })
    };
    return this.httpClient.get<Account[]>(this.accountListURL, httpOptions);
  }

  // to retrieve all pending requests
  getPendingRequests(): Observable<Exclusionrequest[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.jwtToken}`
      })
    };
    return this.httpClient.get<Exclusionrequest[]>(this.exclusionRequests, httpOptions);
  }

}

