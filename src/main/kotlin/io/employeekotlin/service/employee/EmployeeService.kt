package io.employeekotlin.service.employee
import io.employeekotlin.client.EmployeeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

    fun getEmployeeById(id: Long): ResponseEntity<EmployeeResponse>{
        if(!employeeRepository.existsById(id)){
            return ResponseEntity(EmployeeResponse(
                    1, "Employee with the Provided ID not found",
                    null, ""), HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(EmployeeResponse(
                0, "",
                employeeRepository.getReferenceById(id),
                ""), HttpStatus.OK)
    }

    fun addEmployee(employee: Employee): ResponseEntity<EmployeeResponse>{
        val existingEmployee = employeeRepository.existsByEmail(employee.email)
        if(existingEmployee){
            return ResponseEntity(EmployeeResponse(
                            1,
                    "Employee with the email already exists",
                    null, "Email already taken"),
                    HttpStatus.CONFLICT)
        }
        return ResponseEntity(EmployeeResponse(
                0, "",
                employeeRepository.save(employee), ""),
                HttpStatus.CONFLICT)
    }

    fun deleteEmployee(id: Long) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Employee not found with id: $id")
        }
    }
}