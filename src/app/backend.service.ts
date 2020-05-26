import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';
import { Response } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  authenticated = false;
  jwtToken = "";
  errorMessage = "";

  constructor(private http: HttpClient) { }

     private validationURL = '/authenticate';
     private accountListURL = '/api/account/v0/retrieveAll';

      authenticate(credentials, callback) {

        const httpOptions = {
          headers: new HttpHeaders({
            'Content-Type': 'application/json'
          })
        };

        this.http.post<string>(this.validationURL, credentials, httpOptions).subscribe(response => {
          if (response['jwtToken']) {
            this.authenticated = true;
            this.jwtToken = response['jwtToken']

          } else {
            if (response['errorMessage']) {
              this.errorMessage = response['errorMessage'];
            }
            this.authenticated = false;
          }
          return callback && callback();
        });
      }

}
