import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Book } from '../book';
import { BookDetailsService } from '../book-details/book-details.service';
import { User } from '../user';
import { CartService } from './cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartList: Book[] = [];
  public sessionUser:string|null = null;
  public user:any = null;
  public totalPrice:number = 0;

  cartGroup = new FormGroup({
    bookCover: new FormControl(''),
    bookTitle: new FormControl(''),
    bookAuthor: new FormControl(''),
    bookPublisher: new FormControl(''),
    bookPublished: new FormControl(''),
    bookGenre: new FormControl(''),
    bookPrice: new FormControl(''),
    bookSummary: new FormControl('')
  });

  
  constructor(private cartServ:CartService, private bookServ:BookDetailsService) { }

  ngOnInit(): void {
      this.sessionUser = window.sessionStorage.getItem("currentUser");
      if (this.sessionUser != null) {
        this.user = JSON.parse(this.sessionUser);
        this.cartList = this.user.cart;
        this.cartList.forEach((b) => {
          this.totalPrice += b.price;
        });
      }
  }

  public addToBookmarks(book:Book) {
    // remove book from cart
    let user:User = this.user;
    this.cartList.forEach((b, idx) => {
      if(b.isbn == book.isbn) {
        this.cartList.splice(idx,1);
      }
    });
    // set user's cart to updated cart list
    user.cart = this.cartList;
    // add book to bookmarks
    user.bookmarks.push(book);
    // calculate new total price
    this.totalPrice = 0;
    this.cartList.forEach((b) => {
      this.totalPrice += b.price;
    });
    let stringUser = JSON.stringify(user);
    this.bookServ.addBook(stringUser).subscribe(
      response => {
        window.sessionStorage.setItem("currentUser", JSON.stringify(response));
      }
    )
  }

  public addToOrders() {
    let user:User = this.user;
    // move cart to orders
    this.cartList.forEach((b) => {
      user.orders.push(b);
    });
    // clear cart
    this.cartList = [];
    user.cart = this.cartList;
    // zero out price
    this.totalPrice = 0;
    let stringUser = JSON.stringify(user);
    this.bookServ.addBook(stringUser).subscribe(
      response => {
        window.sessionStorage.setItem("currentUser", JSON.stringify(response));
      }
    )
  }

  public remove(book:Book) {
    let user:User=this.user;
    this.cartList.forEach((b, idx) => {
      if(b.isbn == book.isbn) {
        this.cartList.splice(idx,1);
      }
    });
    // set user's cart to updated cart list
    user.cart=this.cartList;
    // calculate new total price
    this.totalPrice = 0;
    this.cartList.forEach((b) => {
      this.totalPrice += b.price;
    });
    let stringUser = JSON.stringify(user);
    this.cartServ.updateUser(stringUser).subscribe(
      response => {
        window.sessionStorage.setItem("currentUser", JSON.stringify(response));
      },
      error => {
        console.warn("This error occurred: " + error);
      }
    );
  }
}
