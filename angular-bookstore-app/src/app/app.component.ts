import { Component, OnInit } from '@angular/core';
import { Book } from './model/books.model';
import { Store } from '@ngrx/store';
import { CreateBookAction, getBooksAction } from 'src/state/books/books.actions';
import { getBooksSelector } from 'src/state/books/books.selectors';
import { Observable } from 'rxjs';
import { CreateBookRequest } from './model/requests/book-response-request';
import { createEffect } from '@ngrx/effects';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  // title = 'angular-bookstore-app';
  bookPrice: number = 0
  title: string = ""
  author: string = ""
  numberOfBooks: number = 0
  writtenDate: string = ""

  books: Observable<Book[]> | undefined


  constructor(
    private _store: Store
  ) { }

  ngOnInit(): void {
    this._store.dispatch(getBooksAction())
    this.books = this._store.select(getBooksSelector)
  }


  onAddBook() {
    const book: CreateBookRequest = this.buildBookingRequest();
    this._store.dispatch(CreateBookAction({ book }));
    this.fetchData()
  }


  buildBookingRequest(): CreateBookRequest {
    const request = {} as CreateBookRequest
    request.author = this.author;
    request.bookPrice = this.bookPrice;
    request.numberOfBooks = this.numberOfBooks
    request.title = this.title;
    request.writtenDate = this.writtenDate
    return request;
  }

  fetchData() {
    window.location.reload()
  }

}
