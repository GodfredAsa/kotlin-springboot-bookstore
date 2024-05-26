import { Component, OnInit } from '@angular/core';
import { Book } from './model/books.model';
import { Store } from '@ngrx/store';
import { getBooksAction } from 'src/state/books/books.actions';
import { getBooks } from 'src/state/books/books.selectors';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'angular-bookstore-app';

  books: Book[] = []

  name: string = "Hello World";

  constructor(
    private _store: Store
  ){}

  ngOnInit(): void {
    this._store.dispatch(getBooksAction())
    this._store.select(getBooks).subscribe((data) => {
      this.books = data
      console.log(this.books);

    })

  }

}
