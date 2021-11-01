import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private urlBase = "http://localhost:9015/users";

  constructor(private http: HttpClient) {}

  public getUsers(): Observable<User[]> {
    const httpHead = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Access-Control-Allow-Origin':'*'
      })
    };
    return this.http.get<User[]>(this.urlBase, httpHead);
  }
}
