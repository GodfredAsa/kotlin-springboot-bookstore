package io.employeekotlin.service.book

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface BookRepository: JpaRepository<Book, Long> {

    @Query("select b from Book b where b.id = ?1")
    override fun findById(id: Long): Optional<Book>

    @Query("select (count(b) > 0) from Book b where b.title = ?1")
    fun existsByTitle(title: String): Boolean

    @Query("select (count(b) > 0) from Book b where b.id = ?1")
    fun findExistingBookById(id: Long): Boolean

    @Transactional
    @Modifying
    @Query("update Book b set b.numberOfBooks = ?1 where b.id = ?2")
    fun updateNumberOfBooksById(numberOfBooks: Int, id: Long): Int
}