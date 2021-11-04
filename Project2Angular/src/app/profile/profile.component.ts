import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from '../user';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public sessionUser:string|null = null;
  public user:any=null;
  // public user: User = new User("","","","","","",[],[],[]);
  public errorMessage:string ="error message";
  public successMessage:string ="success message";
  public passuser:User | undefined;

  passwordGroup = new FormGroup({
    currentP: new FormControl(''),
    newP: new FormControl(''),
    newP2: new FormControl('')
  });

  constructor(private profileServ:ProfileService) { }

  ngOnInit(): void {
    // Gets the current session's user
    this.sessionUser = window.sessionStorage.getItem("currentUser");
    if (this.sessionUser != null) {
      this.user = JSON.parse(this.sessionUser);
    }
    // this.profileServ.getAUser().subscribe(
    //   response =>{
    //     console.log(response);
    //     this.user=response;
    //   }
    // )
  }

  public submitPassword(passwords: FormGroup){
    if(passwords.value.currentP===this.user.password){
      if(passwords.value.newP===passwords.value.newP2){
        let user2:User = new User(this.user.username,passwords.value.newP,this.user.firstname,this.user.lastname,this.user.email,this.user.roleid,this.user.orders,this.user.cart,this.user.bookmarks);
        let stringuser= JSON.stringify(user2); 
        this.profileServ.updateUser(stringuser).subscribe(
          response =>{
            console.log(response);
            this.user=response;
          }
        )


        this.errorMessage ="";
        this.successMessage ="Successfully updated password";
      } else {
        this.errorMessage ="New passwords do not match";
        this.successMessage ="";
      }
    } else {
      this.errorMessage ="Current password is incorrect";
      this.successMessage ="";
    }
    // console.log("in submitpassword")
    // console.log(passwords);
    // console.log(passwords.value);
    // console.log(passwords.value.currentP);
    // console.log(passwords.value.newP);
    // console.log(passwords.value.newP2);
    // console.log(food);
    // let stringFood = JSON.stringify(food.value);
    // this.foodServ.insertFood(stringFood).subscribe(
    //   response => {
    //     console.log(response);
    //     this.foodList.push(response);
    //   },
    //   error =>{
    //     console.warn("there was an error ", error);
    //   }
    // )
  }

}
