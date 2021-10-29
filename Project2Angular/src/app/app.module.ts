import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CatalogComponent } from './catalog/catalog.component';
import { BookmarksComponent } from './bookmarks/bookmarks.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'bookmarks', component: BookmarksComponent},
  {path: 'cart', component: CartComponent},
  {path: 'search/:keyword', component: CatalogComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    LoginComponent,
    RegistrationComponent,
    CatalogComponent,
    BookmarksComponent,
    CartComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule, RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
