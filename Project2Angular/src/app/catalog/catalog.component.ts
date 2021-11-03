import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  public keyword:string|null = null;
  public filter:any;

  constructor() { }

  ngOnInit(): void {
    this.keyword = window.sessionStorage.getItem("keyword");
    var sessionFilter = window.sessionStorage.getItem("filter");
    this.filter = sessionFilter?.charAt(sessionFilter.length-2);

    switch(this.filter) {
      case '1': {
        console.log("Author");
        // call function to get book by author
        break;
      }
      case '2': {
        console.log("Title");
        // call function to get book by title
        break;
      }
      case '3': {
        console.log("ISBN");
        // call function to get book by ISBN
        break;
      }
      case '4': {
        console.log("Publisher");
        // call function to get book by publisher
        break;
      }
      case '5': {
        console.log("Genre");
        // call function to get book by genre
        break;
      }
    }
  }

}
