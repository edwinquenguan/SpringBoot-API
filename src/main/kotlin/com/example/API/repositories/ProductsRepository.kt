package com.example.API.repositories

import com.example.API.models.Product
import com.example.API.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository


@Repository
class ProductsRepository {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun getUsers(): List<User> {
        return jdbcTemplate.query("SELECT * FROM USERS", RowMapper<User> {rs, _ ->
            User(
                rs.getInt("user_id"),
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("user_first_surname"),
                rs.getString("user_second_surname"),
                rs.getString("user_phone"),
                rs.getString("user_email"),
                rs.getString("user_password"),
                rs.getString("user_address"),
                rs.getString("user_city"),
                rs.getString("user_date"),
            )
        })
    }

    fun createUser(user: User): Int {
        val sql = """
            INSERT INTO USERS(
             var input_order_id: Int,
    var subcategory_id: Int,
    var product_id : Int,
    var product_serial: Int,
    var product_name: String,
    var product_model: String,
    var product_stock: Int,
    var product_garanty_input: String
            rol_id,
            user_name,
            user_first_surname,
            user_second_surname,
            user_phone,
            user_email,
            user_password,
            user_address,
            user_city)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            user.rol_id,
            user.user_name,
            user.user_first_surname,
            user.user_second_surname,
            user.user_phone,
            user.user_email,
            user.user_password,
            user.user_address,
            user.user_city
        )
    }

    fun updateUser(user: User, id: Int): Int {
        val sql = """
            UPDATE USERS SET
                user_name = ?,
                user_first_surname = ?,
                user_second_surname = ?,
                user_phone = ?,
                user_email = ?,
                user_password = ?,
                user_address = ?,
                user_city = ?
            WHERE user_id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            user.user_name,
            user.user_first_surname,
            user.user_second_surname,
            user.user_phone,
            user.user_email,
            user.user_password,
            user.user_address,
            user.user_city,
            id
        )
    }

    fun deleteUser(id: Int): Int {
        val sql = """
            DELETE FROM USERS
            WHERE user_id = ?
        """.trimIndent()

        return jdbcTemplate.update(sql, id)
    }


}