### PROCEDURES FOR IMPLEMENTING NGRX IN ANGULAR FOR STATE MANAGEMENT

export interface User {
  name: string;
  email: string;
  password: string;
}

##### CREATE ACTIONS 
// src/app/store/actions/user.actions.ts
import { createAction, props } from '@ngrx/store';
import { User } from '../../models/user.model';

export const loadUsers = createAction('[User] Load Users');
export const loadUsersSuccess = createAction('[User] Load Users Success', props<{ users: User[] }>());
export const loadUsersFailure = createAction('[User] Load Users Failure', props<{ error: any }>());

export const addUser = createAction('[User] Add User', props<{ user: User }>());
export const addUserSuccess = createAction('[User] Add User Success', props<{ user: User }>());
export const addUserFailure = createAction('[User] Add User Failure', props<{ error: any }>());

export const updateUser = createAction('[User] Update User', props<{ user: User }>());
export const updateUserSuccess = createAction('[User] Update User Success', props<{ user: User }>());
export const updateUserFailure = createAction('[User] Update User Failure', props<{ error: any }>());

export const deleteUser = createAction('[User] Delete User', props<{ email: string }>());
export const deleteUserSuccess = createAction('[User] Delete User Success', props<{ email: string }>());
export const deleteUserFailure = createAction('[User] Delete User Failure', props<{ error: any }>());

#### CREATE REDUCERS
// src/app/store/reducers/user.reducer.ts
import { createReducer, on } from '@ngrx/store';
import { User } from '../../models/user.model';
import * as UserActions from '../actions/user.actions';

export interface State {
  users: User[];
  error: any;
}

export const initialState: State = {
  users: [],
  error: null,
};

const _userReducer = createReducer(
  initialState,
  on(UserActions.loadUsersSuccess, (state, { users }) => ({ ...state, users })),
  on(UserActions.loadUsersFailure, (state, { error }) => ({ ...state, error })),
  on(UserActions.addUserSuccess, (state, { user }) => ({ ...state, users: [...state.users, user] })),
  on(UserActions.addUserFailure, (state, { error }) => ({ ...state, error })),
  on(UserActions.updateUserSuccess, (state, { user }) => ({
    ...state,
    users: state.users.map(u => (u.email === user.email ? user : u)),
  })),
  on(UserActions.updateUserFailure, (state, { error }) => ({ ...state, error })),
  on(UserActions.deleteUserSuccess, (state, { email }) => ({
    ...state,
    users: state.users.filter(u => u.email !== email),
  })),
  on(UserActions.deleteUserFailure, (state, { error }) => ({ ...state, error }))
);

export function userReducer(state: State | undefined, action: Action) {
  return _userReducer(state, action);
}

#### CREATE EFFECTS
// src/app/store/effects/user.effects.ts
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { UserService } from '../../services/user.service';
import * as UserActions from '../actions/user.actions';
import { User } from '../../models/user.model';

@Injectable()
export class UserEffects {

  constructor(
    private actions$: Actions,
    private userService: UserService
  ) {}

  loadUsers$ = createEffect(() =>
    this.actions$.pipe(
      ofType(UserActions.loadUsers),
      mergeMap(() => this.userService.getUsers()
        .pipe(
          map(users => UserActions.loadUsersSuccess({ users })),
          catchError(error => of(UserActions.loadUsersFailure({ error })))
        ))
    )
  );

  addUser$ = createEffect(() =>
    this.actions$.pipe(
      ofType(UserActions.addUser),
      mergeMap(action => this.userService.addUser(action.user)
        .pipe(
          map(user => UserActions.addUserSuccess({ user })),
          catchError(error => of(UserActions.addUserFailure({ error })))
        ))
    )
  );

  updateUser$ = createEffect(() =>
    this.actions$.pipe(
      ofType(UserActions.updateUser),
      mergeMap(action => this.userService.updateUser(action.user)
        .pipe(
          map(user => UserActions.updateUserSuccess({ user })),
          catchError(error => of(UserActions.updateUserFailure({ error })))
        ))
    )
  );

  deleteUser$ = createEffect(() =>
    this.actions$.pipe(
      ofType(UserActions.deleteUser),
      mergeMap(action => this.userService.deleteUser(action.email)
        .pipe(
          map(() => UserActions.deleteUserSuccess({ email: action.email })),
          catchError(error => of(UserActions.deleteUserFailure({ error })))
        ))
    )
  );
}


#### SERVICES 
// src/app/services/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${user.email}`, user);
  }

  deleteUser(email: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${email}`);
  }
}


##### Register the NgRx Store, Effects, and HttpClientModule.
// src/app/app.module.ts
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';

import { AppComponent } from './app.component';
import { userReducer } from './store/reducers/user.reducer';
import { UserEffects } from './store/effects/user.effects';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    StoreModule.forRoot({ userState: userReducer }),
    EffectsModule.forRoot([UserEffects]),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
