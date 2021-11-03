import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginService } from './login.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loggedIn:boolean = false;
  submitted:boolean = false;
  userList: User[] = [];
  loginGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    roleid: new FormControl(''),
    orders: new FormControl([]),
    cart: new FormControl([]),
    bookmarks: new FormControl([])
  });

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loggedIn=false;
    this.submitted=false;
    /* this.loginService.getAllUsers().subscribe(
      response => {
        this.userList = response;
      }
    ) */
  }

  public login(user: FormGroup): void {
    this.submitted=true;
    let stringUser = JSON.stringify(user.value);
    this.loginService.validateUser(stringUser).subscribe(
      response => {
        this.loggedIn=true;
        sessionStorage.setItem("currentUser", JSON.stringify(response));
        window.location.href="http://localhost:4200/";
      },
      error => {
        console.warn("This error occurred: " + error);
      }
    )
  }
}
