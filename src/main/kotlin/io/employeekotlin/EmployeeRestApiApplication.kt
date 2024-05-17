package io.employeekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmployeeRestApiApplication

fun main(args: Array<String>) {
	runApplication<EmployeeRestApiApplication>(*args)
}
