import { createReducer, on } from "@ngrx/store";
import { Book } from "src/app/model/books.model";
import { CreateBookAction, CreateBookFailure, CreateBookSuccess, getBooksAction, getBooksFailAction, getBooksSuccessAction } from "./books.actions";


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

// CREATE BOOK FEATURE

export interface CreateBookState {
  book: any;
  loading: boolean;
  error?: null;
}

export const createBookInitialState: CreateBookState = {
  book: null,
  loading: false,
  error: null,
};

// on(UserActions.addUserSuccess, (state, { user }) => ({ ...state, users: [...state.users, user] })),

export const createBookReducer = createReducer(
  createBookInitialState,
  on(CreateBookAction, (state) => ({
    ...state,
    loading: true,
    error: null
  })),

  on(CreateBookSuccess, (state, {book}) => ({
    ...state,
    book: book,
    loading: false
  })),

  on(CreateBookFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error: error
  }))
)
