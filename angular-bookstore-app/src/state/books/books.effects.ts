import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { BookService } from "src/app/services/book.service";
import { getBooksAction, getBooksFailAction, getBooksSuccessAction } from "./books.actions";
import { catchError, map, of, pipe, switchMap } from "rxjs";

@Injectable()
export class BooksEffects{
  constructor(
    private _actions$: Actions,
    private _bookService: BookService
  ){}
  loadBooks$ = createEffect( () =>  this._actions$.pipe(
    ofType(getBooksAction),
    switchMap(() => {
      return this._bookService.findAllBooks().pipe(
        map((response) => getBooksSuccessAction({response})),
        catchError((error) => of(getBooksFailAction({error: error.toString})))
      )
    })


  ))

}
