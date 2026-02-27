package com.example.API.repositories

import com.example.API.service.Security
import com.example.API.models.Login
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AuthRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var security: Security

    fun login(login: Login) : Map<String, Any> {
        val user = usersRepository.getUserByEmail(login.email)

        if (user.isEmpty()) {
            return mapOf(
                "sucess" to false,
                "message" to "Usuario no encontrado"
            )
        }

        val userInfo = user.first()

        return security.verifyPassword(userInfo, login.password)
    }
}