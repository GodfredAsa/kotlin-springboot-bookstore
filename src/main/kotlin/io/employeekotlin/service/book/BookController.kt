package io.employeekotlin.service.book

import io.employeekotlin.client.BookResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = ["http://localhost:4200"])
class BookController(private val bookService: BookService) {

    @PostMapping
    fun createBook(@RequestBody book: Book): ResponseEntity<BookResponse>{
        return bookService.createBook(book)
    }

    @GetMapping("/{bookId}")
    fun findBookById(@PathVariable bookId: Long): ResponseEntity<BookResponse>{
        return bookService.findBookById(bookId)
    }

    @GetMapping
    fun findAllBooks(): ResponseEntity<List<Book>>{
        return ResponseEntity(bookService.findAllBooks(), HttpStatus.OK)
    }


}