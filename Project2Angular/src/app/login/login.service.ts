import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private urlBase = "http://localhost:9015/bookstore/login";

  constructor(private http: HttpClient) {}

  public getAllUsers(): Observable<User[]>{
    const httpHead = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Access-Control-Allow-Origin':'*'
      })
    };
    return this.http.get<User[]>(this.urlBase, httpHead);
  }

  public validateUser(user:string): Observable<User[]> {
    const httpHead = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Access-Control-Allow-Origin':'*'
      })
    };
    return this.http.post<User[]>(this.urlBase, user, httpHead);
  }

}
