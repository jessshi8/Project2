import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { CatalogService } from './catalog.service';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  public keyword:string|null = null;
  public filter:any;
  bookList:Book[] = [];

  constructor(private catalogServ:CatalogService) { }

  ngOnInit(): void {
    this.keyword = window.sessionStorage.getItem("keyword");
    var sessionFilter = window.sessionStorage.getItem("filter");
    this.filter = sessionFilter?.charAt(sessionFilter.length-2);

    if (this.keyword != null) {
      console.log(this.keyword);
      switch(this.filter) {
        case '1': {
          console.log("Author");
          this.catalogServ.getBooksByAuthor(this.keyword).subscribe(
            response=>{
              console.log(response);
              this.bookList=response;
            }
          );
          break;
        }
        case '2': {
          console.log("Title");
          this.catalogServ.getBooksByTitle(this.keyword).subscribe(
            response=>{
              console.log(response);
              this.bookList=response;
            }
          );
          break;
        }
        case '3': {
          console.log("ISBN");
          this.catalogServ.getBooksByISBN(this.keyword).subscribe(
            response=>{
              console.log(response);
              this.bookList=response;
            }
          );
          break;
        }
        case '4': {
          console.log("Publisher");
          this.catalogServ.getBooksByPublisher(this.keyword).subscribe(
            response=>{
              console.log(response);
              this.bookList=response;
            }
          );
          break;
        }
        case '5': {
          console.log("Genre");
          this.catalogServ.getBooksByGenre(this.keyword).subscribe(
            response=>{
              console.log(response);
              this.bookList=response;
            }
          );
          break;
        }
      }
    }
  }
}
