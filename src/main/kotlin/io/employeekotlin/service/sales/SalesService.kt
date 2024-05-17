package io.employeekotlin.service.sales

import io.employeekotlin.client.SalesBookingHttpResponse
import io.employeekotlin.client.SalesRequest
import io.employeekotlin.client.SalesResponse
import org.springframework.http.ResponseEntity
import java.net.http.HttpResponse
import java.util.Optional

interface SalesService {
    fun createSale(request: SalesRequest): ResponseEntity<SalesBookingHttpResponse>
    fun findAllSales(): List<Sales>
    fun findSalesById(saleId: Long): Optional<Sales>
}