package io.employeekotlin.service.employee

import io.employeekotlin.client.EmployeeResponse
import jakarta.annotation.security.RolesAllowed
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: EmployeeService) {
    @GetMapping
    fun getAllEmployees(): List<Employee> = employeeService.getAllEmployees()

    @GetMapping("/{id}")
    @RolesAllowed("OPERATIONS") // DID NOT WORK WITH THE ROLES.
    fun getEmployeeById(@PathVariable id: Long): ResponseEntity<EmployeeResponse> {
        return employeeService.getEmployeeById(id)
    }

    @PostMapping
    fun addEmployee(@RequestBody employee: Employee): ResponseEntity<EmployeeResponse> {
        return employeeService.addEmployee(employee)
    }

// delete employee WORKED WITH THE ROLES
    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<EmployeeResponse> {
    if(employeeService.getEmployeeById(id).body?.data?.role != Role.FINANCE){
        println("================ ROLES ==============")
        return ResponseEntity(EmployeeResponse(1, "", null, ""), HttpStatus.UNAUTHORIZED)
    }else{
        println("================ NOT ROLES ==============")
        employeeService.deleteEmployee(id)
        return ResponseEntity.noContent().build()
    }

    }
}