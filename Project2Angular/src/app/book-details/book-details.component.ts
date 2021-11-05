import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {Book} from '../book';
import { CatalogService } from '../catalog/catalog.service';
import { User } from '../user';
import { BookDetailsService } from './book-details.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book: Book[] = [];
  public sessionUser:string|null = null;
  public user!: User;

  constructor(private route: ActivatedRoute, private catalogServ: CatalogService, private bookService: BookDetailsService) { }

  ngOnInit(): void {
    //Getting the book isbn from the current route.
    const routeParams = this.route.snapshot.paramMap;
    const bookIsbnFromRoute = String(routeParams.get('bookIsbn'));

    //finding the book that correspond with the isbn provide in route.
    //this.book= Book.find(books => book.isbn === bookIsbnFromRoute);
    this.catalogServ.getBooksByISBN(bookIsbnFromRoute).subscribe(
      response=>{
        this.book=response;
      }
    );
    this.sessionUser = window.sessionStorage.getItem("currentUser");
    if (this.sessionUser != null) {
      this.user = JSON.parse(this.sessionUser);
    }
  }

  public addToCart() {
    if (this.user != null) {
      let user:User = this.user;
      user.cart.push(this.book[0]);
      console.log(user.cart);
      let stringUser = JSON.stringify(user);
      this.bookService.addBook(stringUser).subscribe(
        response => {
          console.log(response);
          window.sessionStorage.setItem("currentUser", JSON.stringify(response));
        },
        error => {
          console.warn("This error occurred: " + error);
        }
      )
    }
  }

  public addToBookmarks() {
    if (this.user != null) {
      let user:User = this.user;
      user.bookmarks.push(this.book[0]);
      console.log(user.bookmarks);
      let stringUser = JSON.stringify(user);
      this.bookService.addBook(stringUser).subscribe(
        response => {
          console.log(response);
          window.sessionStorage.setItem("currentUser", JSON.stringify(response));
        },
        error => {
          console.warn("This error occurred: " + error);
        }
      )
    }
  }
}
