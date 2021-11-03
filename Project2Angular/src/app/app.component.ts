import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public logout() {
    console.log("user logged out");
    window.onunload=function(){null};
    sessionStorage.clear();
  }
}


