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
  userList: User[] = [];
  loginGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    roleid: new FormControl('')
  });

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.getAllUsers().subscribe(
      response => {
        this.userList = response;
      }
    )
  }

  public login(user: FormGroup): void {
    let stringUser = JSON.stringify(user.value);
    this.loginService.validateUser(stringUser).subscribe(
      response => {
        sessionStorage.setItem("currentUser", JSON.stringify(response));
      },
      error => {
        console.warn("This error occurred: " + error);
      }
    )
  }
}
