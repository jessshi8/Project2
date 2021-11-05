import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public sessionUser:string|null = null;
  public user:any = null;
  filterForm!: FormGroup;
  filters = [
    {id: 1, name: "Author"},
    {id: 2, name: "Title"},
    {id: 3, name: "ISBN"},
    {id: 4, name: "Publisher"},
    {id: 5, name: "Genre"}
  ]

  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.sessionUser = window.sessionStorage.getItem("currentUser");
    if (this.sessionUser != null) {
      this.user = JSON.parse(this.sessionUser);
      console.log(this.user);
    }
    this.filterForm = this.formBuilder.group({
      filter: [null]
    });
  }

  searchBooks(keyword : string) {
    sessionStorage.setItem("keyword", JSON.stringify(keyword));
    sessionStorage.setItem("filter", JSON.stringify(this.filterForm.value));
    this.router.navigateByUrl('/search/'+keyword);
  }
}
