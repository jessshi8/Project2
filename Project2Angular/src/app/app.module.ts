import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CatalogComponent } from './catalog/catalog.component';
import { BookmarksComponent } from './bookmarks/bookmarks.component';
import { CartComponent } from './cart/cart.component';
import { ProfileComponent } from './profile/profile.component';
import { OrdersComponent } from './orders/orders.component';
import { HomeComponent } from './home/home.component';
import { BookDetailsComponent } from './book-details/book-details.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'bookmarks', component: BookmarksComponent},
  {path: 'cart', component: CartComponent},
  {path: 'search/:keyword', component: CatalogComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'search/:keyword', component: CatalogComponent},
  {path: 'book/:bookIsbn', component: BookDetailsComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    CatalogComponent,
    BookmarksComponent,
    CartComponent,
    ProfileComponent,
    OrdersComponent,
    ProfileComponent,
    HomeComponent,
    BookDetailsComponent

  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule, RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
