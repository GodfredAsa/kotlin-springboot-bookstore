import { createAction, props } from "@ngrx/store";
import { Book } from "src/app/model/books.model";
import { CreateBookRequest } from "src/app/model/requests/book-response-request";

export const getBooksAction = createAction(
  '[Book Actions] Get Books'
)

export const getBooksSuccessAction = createAction(
  '[Book Actions] Get Books Success',
  props<{response: Book[]}>()
)

export const getBooksFailAction = createAction(
  '[Book Actions] Get Books Fail',
  props<{error: string}>()
)

export const CreateBookAction = createAction(
  '[Book] Add Book',
  props<{ book: CreateBookRequest }>()
)

export const CreateBookSuccess = createAction(
  '[Book] Add Book Success',
  props<{ book: CreateBookRequest }>()
)

export const CreateBookFailure = createAction(
  '[Book] Add Book Failure',
  props<{error: any }>()
)







