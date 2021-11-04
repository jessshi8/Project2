import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {Book} from '../book';
import { CatalogService } from '../catalog/catalog.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book: Book[] = [];

  constructor(private route: ActivatedRoute, private catalogServ: CatalogService) { }

  ngOnInit(): void {
    //Getting the book isbn from the current route.
    const routeParams = this.route.snapshot.paramMap;
    const bookIsbnFromRoute = String(routeParams.get('bookIsbn'));

    //finding the book that correspond with the isbn provide in route.
    //this.book= Book.find(books => book.isbn === bookIsbnFromRoute);
    this.catalogServ.getBooksByISBN(bookIsbnFromRoute).subscribe(
      response=>{
        console.log(response);
        this.book=response;
      })
    
  }

}
