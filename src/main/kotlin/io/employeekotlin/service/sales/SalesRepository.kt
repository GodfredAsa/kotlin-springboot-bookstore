package io.employeekotlin.service.sales

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesRepository: JpaRepository<Sales, Long> {
}