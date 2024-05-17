package io.employeekotlin.service.sales

import io.employeekotlin.client.SalesBookingHttpResponse
import io.employeekotlin.client.SalesRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sales")
class SalesController(private val salesService: SalesService) {

    @PostMapping
    fun createSale(@RequestBody request: SalesRequest): ResponseEntity<SalesBookingHttpResponse> {
        return salesService.createSale(request)
    }

    @GetMapping
    fun findAllSales(): List<Sales> {
        return salesService.findAllSales()
    }
}