package io.employeekotlin.service.employee
import io.employeekotlin.client.EmployeeResponse
import io.employeekotlin.constants.CustomErrorMessage
import io.employeekotlin.constants.EmployeeConstants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

    fun getEmployeeById(id: Long): ResponseEntity<EmployeeResponse>{
        if(!employeeRepository.existsById(id)){
            return ResponseEntity(EmployeeResponse(1, EmployeeConstants.NOT_FOUND, null,
                    CustomErrorMessage.NULL_ERROR), HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(EmployeeResponse(
                0, null, employeeRepository.getReferenceById(id), null), HttpStatus.OK)
    }

    fun addEmployee(employee: Employee): ResponseEntity<EmployeeResponse>{
        val existingEmployee = employeeRepository.existsByEmail(employee.email)
        if(existingEmployee){
            return ResponseEntity(EmployeeResponse(
                    1, EmployeeConstants.EMAIL_EXISTS,
                    null, EmployeeConstants.EMAIL_EXISTS),
                    HttpStatus.CONFLICT)
        }
        return ResponseEntity(EmployeeResponse(
                0, null,
                employeeRepository.save(employee), null),
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