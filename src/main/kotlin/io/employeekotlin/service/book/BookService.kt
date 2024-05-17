package io.employeekotlin.service.book

import io.employeekotlin.client.BookResponse
import org.springframework.http.ResponseEntity
import java.util.*

interface BookService {
    fun createBook(book: Book): ResponseEntity<BookResponse>
    fun findBookById(bookId: Long) : ResponseEntity<BookResponse>
    fun findAllBooks(): List<Book>

}