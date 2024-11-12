package io.employeekotlin.service.employee

import io.employeekotlin.service.sales.Sales
import jakarta.persistence.*

@Entity
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val firstName: String,
        val lastName: String,
        val email: String,
        val wallet: Double,
        @Enumerated(EnumType.STRING)
        val role: Role
){
        class Builder(
                var id: Long = 0,
                var firstName: String = "",
                var lastName: String = "",
                var email: String = "",
                var wallet: Double = 0.0,
                var role: Role = Role.OPERATIONS
        ) {
                fun firstName(firstName: String) = apply { this.firstName = firstName }
                fun lastName(lastName: String) = apply { this.lastName = firstName }
                fun email(email: String) = apply { this.email = email }
                fun wallet(wallet: Double) = apply { this.wallet = wallet }
                fun role(role: Role) = apply { this.role = role}

                fun employeeId(employeeId: Long) = apply { this.id = id }
                fun build() = Employee(id, firstName, lastName, email, wallet, role)
        }
}
