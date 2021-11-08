import { Component } from '@angular/core';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public user:any= new User("","","","","","",[],[],[]);
  public sessionUser:string|null = "";
  public loggedin: string="true";

  public logout() {
    this.loggedin="false";
    //console.log("user logged out");
    window.onunload=function(){null};
    sessionStorage.clear();
  }

  public readcustomerboolean():boolean  {
    //console.log(this.readcustomerusername());
    this.sessionUser = window.sessionStorage.getItem("currentUser");
    if (this.sessionUser != null) {
      this.user = JSON.parse(this.sessionUser);
    }
    if(window.sessionStorage.getItem("currentUser")==null){
      return true;
    }
    return false;
  }

}


