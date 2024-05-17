package io.employeekotlin.service.sales

import jakarta.persistence.*

@Entity
@Table(name = "sales")
class Sales private constructor(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        val amount: Double,
        val bookId: Long,
        val employeeId: Long
){
        class Builder(
                var id: Long = 0,
                var amount: Double = 0.0,
                var bookId: Long = 0,
                var employeeId: Long = 0
        ) {
                fun amount(amount: Double) = apply { this.amount = amount }
                fun bookId(bookId: Long) = apply { this.bookId = bookId }
                fun employeeId(employeeId: Long) = apply { this.employeeId = employeeId }
                fun build() = Sales(id, amount, bookId, employeeId)
        }
}
