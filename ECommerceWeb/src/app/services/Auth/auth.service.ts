import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';



const BASIC_URL="http://localhost:8089/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,private userStorageService : UserStorageService) { }

  register(signupRequest:any): Observable<any>{
    return this.http.post(BASIC_URL+"sign-up",signupRequest);
  }

  login(username : String, password) : any{
    const headers =new HttpHeaders().set('Content-type','application/json');
    const body={username,password};

    return this.http.post(BASIC_URL+'auth',body,{headers,observe:'response'}).pipe(
      map((res)=>{
        const token =res.headers.get('authorization').substring(7);
        const user=res.body;
        console.log(user)
        if(token && body)
          {
              this.userStorageService.saveToken(token);
              this.userStorageService.saveUser(user);
              return true;
          }
          return false;
      })
    )
  }
}
