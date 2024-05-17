package io.employeekotlin.client

data class SalesResponse(
        val saleId: Long,
        val amount: Double,
        val data: SalesBooking,
)
