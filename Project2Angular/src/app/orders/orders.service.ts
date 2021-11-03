import { Book } from './../book';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  //private urlBase = "http://localhost:9015/bookstore/books";
  public sessionUser:string|null = null;
  public user:any = null;

  constructor() { }
  
    public getOrders(): Observable<Book[]>{
      this.sessionUser = window.sessionStorage.getItem("currentUser");
      if (this.sessionUser != null) {
        this.user = JSON.parse(this.sessionUser);
      }
      return this.user.orders;
    }

  // public getAllBooks(): Observable<Book[]>{
  //   const httpHead = {
  //     headers: new HttpHeaders({
  //       'Content-Type':'application/json',
  //       'Access-Control-Allow-Origin':'*'
  //     })
  //   };
  //   return this.http.get<Book[]>(this.urlBase, httpHead);
  // }
  // public insertBook(book:any): Observable<Book>{
  //   const httpHead = {
  //     headers: new HttpHeaders({
  //       'Content-Type':'application/json',
  //       'Access-Control-Allow-Origin':'*'
  //     })
  //   };
  //   return this.http.post<Book>(this.urlBase, book, httpHead);
 
}
