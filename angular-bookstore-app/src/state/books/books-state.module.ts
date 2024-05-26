import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { StoreModule } from "@ngrx/store";
import { bookFeatureKey, booksReducer } from "./books.reducer";
import { EffectsModule } from "@ngrx/effects";
import { BooksEffects } from "./books.effects";

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature(bookFeatureKey, booksReducer),
    EffectsModule.forFeature(BooksEffects)
  ],
  exports: []
})
export class BookStateModule{

}
