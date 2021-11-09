import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public user:any=null;
  public sessionUser:string|null = "";
  public loggedin: string="true";

  public logout() {
    //console.log("user logged out");
    window.onunload=function(){null};
    sessionStorage.clear();
    window.location.href=window.location.origin + "/index.html";
  }

  public readcustomerboolean():boolean  {
    if(window.sessionStorage.getItem("currentUser")==null){
      return true;
    }
    return false;
  }
}


