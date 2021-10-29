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
    password: new FormControl('')
  });

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.getUsers().subscribe(
      response => {
        console.log(response);
        this.userList=response;
      }
    );
  }

  public login(user: FormGroup): void {
    console.log(user);
  }
}
