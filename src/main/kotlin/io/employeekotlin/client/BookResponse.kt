package io.employeekotlin.client

import io.employeekotlin.service.book.Book

data class BookResponse(
        val status_code: Int?,
        val message: String?,
        val data: Book?,
        val error: String?
)
