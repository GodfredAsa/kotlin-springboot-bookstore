package io.employeekotlin.client

import io.employeekotlin.service.book.Book
import io.employeekotlin.service.employee.Employee

data class EmployeeResponse(
        val status_code: Int?,
        val message: String?,
        val data: Employee?,
        val error: String?
)
