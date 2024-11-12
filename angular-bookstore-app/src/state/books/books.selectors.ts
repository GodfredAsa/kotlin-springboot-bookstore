import { createFeatureSelector, createSelector } from "@ngrx/store";
import { BookState, CreateBookState, bookFeatureKey } from "./books.reducer";
import { InitialState } from "@ngrx/store/src/models";

export const getBooksState = createFeatureSelector<BookState>(bookFeatureKey);

export const getBooksSelector = createSelector(
  getBooksState, (state: BookState) => state.books
)


export const createBookState  = createFeatureSelector<CreateBookState>(bookFeatureKey)
export const createBookSelector = createSelector(
  createBookState, (state: CreateBookState) => state.book
)


