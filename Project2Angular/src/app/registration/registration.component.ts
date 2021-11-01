import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from '../user';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    roleid: new FormControl('Customer')
  });

  constructor(private registrationService: RegistrationService) { }

  ngOnInit(): void {
  }

  public submitUser(user: FormGroup) {
    let stringUser = JSON.stringify(user.value);
    console.log(stringUser);
    this.registrationService.insertUser(stringUser).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.warn("This error occurred: " + error);
      }
    )
  }

}
