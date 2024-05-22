package io.employeekotlin.service.sales

import io.employeekotlin.client.SalesBooking
import io.employeekotlin.client.SalesBookingHttpResponse
import io.employeekotlin.client.SalesRequest
import io.employeekotlin.constants.BookingConstants
import io.employeekotlin.constants.EmployeeConstants
import io.employeekotlin.constants.SalesConstants
import io.employeekotlin.service.book.BookRepository
import io.employeekotlin.service.employee.EmployeeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class SalesServiceImp(
        private val salesRepository: SalesRepository,
        private val bookRepository: BookRepository,
        private val employeeRepository: EmployeeRepository
): SalesService {

    override fun createSale(request: SalesRequest): ResponseEntity<SalesBookingHttpResponse> {
        if(!bookRepository.findExistingBookById(request.bookId)){
            return ResponseEntity(SalesBookingHttpResponse(1, BookingConstants.BOOKING_NOT_FOUND, null, BookingConstants.BOOKING_NOT_EXISTS), HttpStatus.NOT_FOUND)
        }else if (!employeeRepository.existsById(request.employeeId)){
            return ResponseEntity(SalesBookingHttpResponse(1,EmployeeConstants.NOT_FOUND, null, EmployeeConstants.NOT_FOUND), HttpStatus.NOT_FOUND)
        }else{
            val employee = employeeRepository.findById(request.employeeId)
            val book = bookRepository.findById(request.bookId);
            val employeeWallet = employee.get().wallet
            val bookPrice = book.get().bookPrice
            val costOfBook = bookPrice * request.qty
            if(book.get().numberOfBooks < request.qty){
                return ResponseEntity(SalesBookingHttpResponse(2,SalesConstants.INSUFFICIENT_BOOKS, null, SalesConstants.LESS_BOOKS), HttpStatus.BAD_REQUEST)
            }else if(employeeWallet < costOfBook){
                return ResponseEntity(SalesBookingHttpResponse(2,EmployeeConstants.LESS_WALLET_FUNDS, null, EmployeeConstants.USER_HAS_LESS_FUNDS), HttpStatus.BAD_REQUEST)
            }else{
                val sale = buildSale(costOfBook, request.employeeId, request.bookId)
                salesRepository.save(sale)
                employeeRepository.updateWalletById(employee.get().wallet - costOfBook, request.employeeId)
                bookRepository.updateNumberOfBooksById(book.get().numberOfBooks - request.qty, request.bookId)
                val saleBookingResponse = SalesBooking(employee.get().email, book.get().title, costOfBook, request.qty)
                return ResponseEntity(SalesBookingHttpResponse(0,null, saleBookingResponse, null), HttpStatus.CREATED)
            }
        }
    }

    fun buildSale(costOfBook: Double, employeeId: Long, bookId: Long) : Sales {
        return Sales.Builder()
                .amount(costOfBook)
                .employeeId(employeeId)
                .bookId(bookId)
                .build()
    }

    override fun findAllSales():  List<Sales> {
        return salesRepository.findAll()
    }

    override fun findSalesById(saleId: Long): Optional<Sales> {
        return salesRepository.findById(saleId)
    }
}