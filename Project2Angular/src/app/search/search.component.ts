import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchGroup = new FormGroup({
    author: new FormControl(''),
    title: new FormControl(''),
    isbn: new FormControl(''),
    publisher: new FormControl(''),
    genre: new FormControl('')
  });

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  searchBooks(search: FormGroup, keyword : string) {
    console.log('Searched for', keyword);
    this.router.navigateByUrl('/search/'+keyword);
  }

}
