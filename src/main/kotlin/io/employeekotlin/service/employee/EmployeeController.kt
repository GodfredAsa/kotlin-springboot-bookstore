package io.employeekotlin.service.employee

import io.employeekotlin.client.EmployeeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: EmployeeService) {
    @GetMapping
    fun getAllEmployees(): List<Employee> = employeeService.getAllEmployees()

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Long): ResponseEntity<EmployeeResponse> {
        return employeeService.getEmployeeById(id)
    }

    @PostMapping
    fun addEmployee(@RequestBody employee: Employee): ResponseEntity<EmployeeResponse> {
        return employeeService.addEmployee(employee)
    }

// delete employee
    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<Unit> {
        employeeService.deleteEmployee(id)
        return ResponseEntity.noContent().build()
    }
}