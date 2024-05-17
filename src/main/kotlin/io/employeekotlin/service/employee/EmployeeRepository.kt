package io.employeekotlin.service.employee

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long> {


    @Query("select (count(e) > 0) from Employee e where e.email = ?1")
    fun existsByEmail(email: String): Boolean


//    @Transactional
//    @Modifying
//    @Query("update Employee e set e.wallet = ?1")
//    fun updateWalletBy(wallet: Double): Int


    @Transactional
    @Modifying
    @Query("update Employee e set e.wallet = ?1 where e.id = ?2")
    fun updateWalletById(wallet: Double, id: Long): Int
}