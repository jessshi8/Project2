import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  searchGroup = new FormGroup({
    type: new FormControl('')
  });
  public sessionUser:string|null = null;
  public user:any = null;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.sessionUser = window.sessionStorage.getItem("currentUser");
    if (this.sessionUser != null) {
      this.user = JSON.parse(this.sessionUser);
    }
  }

  searchBooks(search: FormGroup, keyword : string) {
    console.log('Searched by', search.value, '; Searched for', keyword);
    this.router.navigateByUrl('/search/'+keyword);
  }

}
