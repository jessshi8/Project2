import { Book } from './../book';
import { FormControl, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { OrdersService } from './orders.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orderList: Book[] = [];

  orderGroup = new FormGroup({
    bookCover: new FormControl(''),
    bookTitle: new FormControl(''),
    bookAuthor: new FormControl(''),
    bookPublisher: new FormControl(''),
    bookPublished: new FormControl(''),
    bookGenre: new FormControl(''),
    bookPrice: new FormControl(''),
    bookSummary: new FormControl('')
  });

  
  constructor(private bookServ:OrdersService) { }

  ngOnInit(): void {
    this.bookServ.getAllBooks().subscribe(
      response =>{
        console.log(response);
        this.orderList=response;
      }
    )
    }
    public submitBook(book: FormGroup){
      console.log(book);
      let stringBook = JSON.stringify(book.value);
      this.bookServ.insertBook(stringBook).subscribe(
        response => {
          console.log(response);
          this.orderList.push(response);
        },
        error =>{
          console.warn("there was an error ", error);
        }
      )
    }
  
  }
  

  

