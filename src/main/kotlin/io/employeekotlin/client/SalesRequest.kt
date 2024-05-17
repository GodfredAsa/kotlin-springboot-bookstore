package io.employeekotlin.client

data class SalesRequest(
        val qty: Int,
        val bookId: Long,
        val employeeId: Long
)
