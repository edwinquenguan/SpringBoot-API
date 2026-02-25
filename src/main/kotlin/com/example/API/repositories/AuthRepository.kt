package com.example.API.repositories

import com.example.API.models.Login
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AuthRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun login(login: Login) {
        val sql = """
            SELECT 
        """.trimIndent()

        jdbcTemplate.update(
            sql,
            login.email,
            login.password
        )
    }
}