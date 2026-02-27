package com.example.API.service

import com.example.API.models.Auth
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date
import javax.crypto.SecretKey

@Component
class Security(
    @Value("\${jwt.secret}") private val secretKey: String,
) {

    var key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun verifyPassword(auth: Auth, password: String) : Map<String, Any> {
        val matches = BCryptPasswordEncoder().matches(password, auth.user_password)

        return if (matches) {
            generateToken(auth)
        } else {
            mapOf(
                "success" to false,
                "message" to "Contraseña incorrecta",
                "a" to BCryptPasswordEncoder().encode(password)
            )
        }
    }

    fun getRoleFromToken(token: String) : String?{
        val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        return try {
            val claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseSignedClaims(token)
                .payload

            claims["role"] as String
        } catch (e: Exception) {
            null
        }
    }

    fun generateToken(user: Auth): Map<String, Any> {
        val expirationTime = Date(System.currentTimeMillis() + 1000 * 60 * 60)
        val now = System.currentTimeMillis()

        val token = Jwts.builder()
            // Payload
            .setSubject(user.user_id.toString())
            .claim("role", user.rol_name)
            .setIssuedAt(Date(now))
            .setExpiration(expirationTime)

            //Signature
            .signWith(SignatureAlgorithm.HS256, secretKey.toByteArray())
            .compact()

        return mapOf(
            "success" to true,
            "token_type" to "Bearer",
            "access_token" to token
        )
    }

    // This function validates whether the token is expired or doesn't have the correct signature
    fun verifyToken(token: String): Boolean{
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(token)

            true
        } catch (e: ExpiredJwtException) {
            false
        } catch (e: Exception) {
            false
        }
    }
}