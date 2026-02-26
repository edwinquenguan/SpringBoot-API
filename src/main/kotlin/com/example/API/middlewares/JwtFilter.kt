package com.example.API.middlewares

import com.example.API.service.Security
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class JwtFilter (
    private val security: Security
)
{
    fun requireRole(token: String, requiredRole: String) : ResponseEntity<Any>? {

        val cleantoken = token.substring(7)

        if (!security.verifyToken(cleantoken)) {
            return ResponseEntity.status(401).body("Token inváldo")
        }

        val role = security.getRoleFromToken(cleantoken)

        if (role != null) {
            return ResponseEntity.status(403).body("No autorizado")
        }

        return null
    }
}