import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/books.model';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { environment } from '../environment';
import { CreateBookRequest } from '../model/requests/book-response-request';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private host: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public findAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(`${this.host}/books`)
  }

  public createBook(request: CreateBookRequest): Observable<any>{
    return this.http.post<any>(`${this.host}/books`, request)
  }
}
