package io.employeekotlin.service.book

import io.employeekotlin.client.BookResponse
import io.employeekotlin.constants.CustomErrorMessage.Companion.BOOKING_CONFLICT
import io.employeekotlin.constants.CustomErrorMessage.Companion.NULL_ERROR
import io.employeekotlin.constants.Message.Companion.BOOK_EXIST
import io.employeekotlin.constants.Message.Companion.NULL_MESSAGE
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookServiceImp(private val bookRepository: BookRepository) : BookService{

    override fun createBook(book: Book): ResponseEntity<BookResponse> {
        return if (bookRepository.existsByTitle(book.title))
            ResponseEntity(BookResponse(1, BOOK_EXIST,null, BOOKING_CONFLICT), HttpStatus.CONFLICT)
        else ResponseEntity(BookResponse(0, NULL_MESSAGE, bookRepository.save(book), NULL_ERROR), HttpStatus.CREATED)
    }

    override fun findBookById(bookId: Long): ResponseEntity<BookResponse>{
        val book = bookRepository.findById(bookId)
        val message = "Book with ID: ${bookId} Not found"
        val notFoundResponse=  BookResponse(1, message, null, "Not found")
        val bookingResponse = BookResponse(0, NULL_MESSAGE, book.get(), NULL_ERROR)

        return if (book.isEmpty) ResponseEntity(notFoundResponse, HttpStatus.NOT_FOUND) else
            ResponseEntity(bookingResponse, HttpStatus.NOT_FOUND)
    }

    override fun findAllBooks(): List<Book> {
        return bookRepository.findAll();
    }

}