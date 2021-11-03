import { Book } from './../book';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private urlBase = "http://localhost:9015/bookstore/books";

  constructor(private http: HttpClient) { }
  
  public getAllBooks(): Observable<Book[]>{
    const httpHead = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Access-Control-Allow-Origin':'*'
      })
    };
    return this.http.get<Book[]>(this.urlBase, httpHead);
  }
  public insertBook(book:any): Observable<Book>{
    const httpHead = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Access-Control-Allow-Origin':'*'
      })
    };
    return this.http.post<Book>(this.urlBase, book, httpHead);
 
}
}
