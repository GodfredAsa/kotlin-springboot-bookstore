import { createFeatureSelector, createSelector } from "@ngrx/store";
import { BookState, bookFeatureKey } from "./books.reducer";

export const getBooksState = createFeatureSelector<BookState>(bookFeatureKey);

export const getBooksSelector = createSelector(
  getBooksState, (state: BookState) => state.books
)
