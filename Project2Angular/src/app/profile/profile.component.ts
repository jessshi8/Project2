import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public sessionUser:string|null = null;
  public user:any = null;

  constructor() { }

  ngOnInit(): void {
    this.sessionUser = window.sessionStorage.getItem("currentUser");
    if (this.sessionUser != null) {
      this.user = JSON.parse(this.sessionUser);
    }
  }

}
