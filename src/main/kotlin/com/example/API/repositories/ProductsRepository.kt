package com.example.API.repositories

import com.example.API.models.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class ProductsRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun getProducts(): List<Product> {
        return jdbcTemplate.query("SELECT * FROM PODUCTS", RowMapper<Product> { rs, _ ->
            Product(
                rs.getInt("input_order_id"),
                rs.getInt("subcategory_id"),
                rs.getInt("product_id "),
                rs.getInt("product_serial"),
                rs.getString("product_name"),
                rs.getString("product_model"),
                rs.getInt("product_stock"),
                rs.getString("product_garanty_input"),
            )
        })
    }


    fun createProduct(product: Product): Int {
        val sql = """
            INSERT INTO PRODUCTS(
            input_order_id,
            subcategory_id,
            product_id ,
            product_serial,
            product_name,
            product_model,
            product_stock,
            product_garanty_input,
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            product.input_order_id,
            product.subcategory_id,
            product.product_id,
            product.product_serial,
            product.product_name,
            product.product_model,
            product.product_stock,
            product.product_garanty_input
        )
    }

    fun updateProduct(product: Product, id: Int): Int {
        val sql = """
            UPDATE PRODUCTS SET
            subcategory_id = ?,
            product_id = ?,
            product_serial = ?,
            product_name = ?,
            product_model = ?,
            product_stock = ?,
            product_garanty_input = ?,
            WHERE product_id = ?    
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            product.input_order_id,
            product.subcategory_id,
            product.product_id,
            product.product_serial,
            product.product_name,
            product.product_model,
            product.product_stock,
            product.product_garanty_input,
            id
        )
    }

    fun deleteProduct(id: Int): Int {
        val sql = """
            DELETE FROM PRODUCT
            WHERE product_id = ?
        """.trimIndent()

        return jdbcTemplate.update(sql, id)
    }
}