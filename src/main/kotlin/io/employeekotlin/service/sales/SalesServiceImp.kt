package io.employeekotlin.service.sales

import io.employeekotlin.client.SalesBooking
import io.employeekotlin.client.SalesBookingHttpResponse
import io.employeekotlin.client.SalesRequest
import io.employeekotlin.client.SalesResponse
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
            return ResponseEntity(SalesBookingHttpResponse(1,"Book Not Found", null, "Book does not exists"), HttpStatus.NOT_FOUND)
        }else if (!employeeRepository.existsById(request.employeeId)){
            return ResponseEntity(SalesBookingHttpResponse(1,"User Not Found", null, "User does not exists"), HttpStatus.NOT_FOUND)
        }else{
            val employee = employeeRepository.findById(request.employeeId)
            val book = bookRepository.findById(request.bookId);
            val employeeWallet = employee.get().wallet
            val bookPrice = book.get().bookPrice
            val costOfBook = bookPrice * request.qty
            if(book.get().numberOfBooks < request.qty){
                return ResponseEntity(SalesBookingHttpResponse(2,"Insufficient Number of Books", null, "Number of books available less than requested"), HttpStatus.BAD_REQUEST)
            }else if(employeeWallet < costOfBook){
                return ResponseEntity(SalesBookingHttpResponse(2,"Insufficient Wallet funds", null, "User has inadequate funds"), HttpStatus.BAD_REQUEST)
            }else{
                val buildSale = Sales.Builder()
                        .amount(costOfBook)
                        .employeeId(request.employeeId)
                        .bookId(request.bookId)
                        .build()
                salesRepository.save(buildSale)
                employeeRepository.updateWalletById(employee.get().wallet - costOfBook, request.employeeId)
                bookRepository.updateNumberOfBooksById(book.get().numberOfBooks - request.qty, request.bookId)
                val saleBookingResponse = SalesBooking(employee.get().email, book.get().title, costOfBook, request.qty)
                return ResponseEntity(SalesBookingHttpResponse(0,null, saleBookingResponse, null), HttpStatus.CREATED)
            }
        }
    }

    override fun findAllSales():  List<Sales> {
        return salesRepository.findAll()
    }

    override fun findSalesById(saleId: Long): Optional<Sales> {
        return salesRepository.findById(saleId)
    }
}