package com.example.API.repositories

import com.example.API.models.Warranty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class WarrantiesRepository {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    fun getWarranties(): List <Warranty>{
        return jdbcTemplate.query("SELECT * FROM WARRANTY_INCIDENTS", RowMapper<Warranty> {rs,_->
            Warranty(
                rs.getInt("warranty_incidents_id"),
                rs.getString("product_serial"),
                rs.getString("warranty_customer"),
                rs.getString("warranty_phone"),
                rs.getString("warranty_address"),
                rs.getString("warranty_description"),
                rs.getString("warranty_link_attachments"),
                rs.getString("warranty_city"),
                rs.getString("warranty_date"),
                rs.getInt("warranty_status")

            )
        }
      )
    }
}