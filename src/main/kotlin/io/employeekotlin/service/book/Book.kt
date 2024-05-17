package io.employeekotlin.service.book

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "books")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val bookPrice: Double,
        val title: String,
        val author: String,
        val writtenDate: String,
        val numberOfBooks: Int
)