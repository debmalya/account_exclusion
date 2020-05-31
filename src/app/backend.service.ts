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
  userName = "";



  constructor(private httpClient: HttpClient) { }

  private validationURL = '/authenticate';
  private accountListURL = '/api/account/v0/retrieveAll';
  private exclusionRequests = '/api/submittedRequest?page=';
  private addRequestURL = '/api/account/v0/exclude';
  private searchPendingRequestsURL = '/api/submittedRequest/search/findByRequestStatus?requestStatus=PENDING&page=';
  private searchAccountNumberURL = '/api/accounts/search/findByAccountNumberContaining?accountNumber=';
  private approvalRequestURL = '/api/approval/v0/all';
  private batchRunURL = '/api/approval/v0/runbatch';
  


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

  batchRun(callback){
    console.log("Backend service calling batch run with http options : " + JSON.stringify(this.generateHttpOptions()));
    this.httpClient.get<string>(this.batchRunURL,this.generateHttpOptions()).subscribe(response =>{
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
  getPendingRequests(pageNo, pageSize): Observable<GetExclusionRequests> {
    return this.httpClient.get<GetExclusionRequests>(this.searchPendingRequestsURL + pageNo + "&size=" + pageSize, this.generateHttpOptions());
    /*.pipe(map(response => response._embedded.requests),
      retry(3), // retry a failed request up to 3 times
      catchError(this.handleError));
    */
  }

  getAllRequests(pageNo,pageSize): Observable<GetExclusionRequests>{
    return this.httpClient.get<GetExclusionRequests>(this.exclusionRequests+pageNo+"&size="+pageSize,this.generateHttpOptions());
  }


  getSearchedAccount(accountNumber,pageNo,pageSize){
    console.log("getSearchedAccount called");
    return this.httpClient.get<GetAccountsRequests>(this.searchAccountNumberURL+ accountNumber+ "&page=" + pageNo + "&size=" + pageSize, this.generateHttpOptions());
  }

  // submit add requests
  addRequests(requestPayload, callback) {
    this.httpClient.post(this.addRequestURL, requestPayload, this.generateHttpOptions()).subscribe(response => {
      return callback && callback();
    });
  }

  // send for pending request approval/reject/cancellation
  approval(requestPayload, callback) {
    this.httpClient.post(this.approvalRequestURL, requestPayload, this.generateHttpOptions()).subscribe(response => {
      return callback & callback();
    });
  }

  private generateHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.jwtToken}`
      })
    };
  }

  addRequests0(requestPayload, callback) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.jwtToken}`
      })
    };

    this.httpClient.post(this.addRequestURL, requestPayload, httpOptions).pipe(
      retry(3), // retry a failed request up to 3 times
      catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
      this.errorMessage = error.error.message;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
      if (error.status === 401) {
        // JWT Token expired, need to relogin
      }
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

  logout() {
    this.jwtToken = "";
    this.authenticated = false;
    this.role = "";
    this.userName = "";
  }

}

interface GetExclusionRequests {
  _embedded: {
    requests: Exclusionrequest[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}

interface GetAccountsRequests {
  _embedded: {
    excludedAccounts: Account[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}

