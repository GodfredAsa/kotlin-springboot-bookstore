import { createAction, props } from "@ngrx/store";
import { Book } from "src/app/model/books.model";

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








