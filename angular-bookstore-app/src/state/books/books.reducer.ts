import { createReducer, on } from "@ngrx/store";
import { Book } from "src/app/model/books.model";
import { getBooksAction, getBooksFailAction, getBooksSuccessAction } from "./books.actions";


export const bookFeatureKey = "books";

export interface BookState{
  books: Book[],
  error?: string,
}


export const initialState : BookState = {
  books: [],
  error: undefined
}


// defining the reducer.
export const booksReducer = createReducer<BookState>(
  initialState,
  on(getBooksSuccessAction, (state, action) => ({
    ...state,
    books: action.response
  })),

  on(getBooksFailAction, (state, action) => ({
    ...state,
    error: action.error
  })),



)
