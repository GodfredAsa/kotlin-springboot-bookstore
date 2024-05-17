package io.employeekotlin.client

data class SalesBookingHttpResponse(
        val status_code: Int,
        val message: String?,
        val data: SalesBooking?,
        val error: String?
)
