//package io.employeekotlin.config
//import io.employeekotlin.service.employee.Employee
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf().disable()
//            .authorizeRequests { requests ->
//                requests.anyRequest("**").permitAll() // Allow access to H2 console
//                    .anyRequest().authenticated()
//            }
//
//
//        // This line is required to allow access to H2 console frames
//
//
//        return http.build()
//    }
//
//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val userDetailsService = InMemoryUserDetailsManager()
//
////        val supportUser = User.withDefaultPasswordEncoder()
////            .username("support")
////            .password("password")
////            .roles("SUPPORT")
////            .build()
////
////        val adminUser = User.withDefaultPasswordEncoder()
////            .username("admin")
////            .password("password")
////            .roles("ADMIN")
////            .build()
////
////        val superAdminUser = User.withDefaultPasswordEncoder()
////            .username("superadmin")
////            .password("password")
////            .roles("SUPERADMIN")
////            .build()
////
////        userDetailsService.createUser(supportUser)
////        userDetailsService.createUser(adminUser)
////        userDetailsService.createUser(superAdminUser)
//
//        return userDetailsService
//    }
//
//}