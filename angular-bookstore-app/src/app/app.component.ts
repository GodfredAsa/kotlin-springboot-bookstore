import { Component, OnInit } from '@angular/core';
import { Book } from './model/books.model';
import { Store } from '@ngrx/store';
import { getBooksAction } from 'src/state/books/books.actions';
import { getBooksSelector } from 'src/state/books/books.selectors';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'angular-bookstore-app';

  books: Observable<Book[]> | undefined

  name: string = "Hello World";

  constructor(
    private _store: Store
  ){}

  ngOnInit(): void {
    this._store.dispatch(getBooksAction())
    this.books = this._store.select(getBooksSelector)
  }

}
