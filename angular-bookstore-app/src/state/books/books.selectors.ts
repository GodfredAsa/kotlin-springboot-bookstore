import { createFeatureSelector, createSelector } from "@ngrx/store";
import { BookState, bookFeatureKey } from "./books.reducer";

export const getBooksState = createFeatureSelector<BookState>(bookFeatureKey);

export const getBooks = createSelector(
  getBooksState, (state: BookState) => state.books
)
