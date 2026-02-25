package com.example.API.controllers

import com.example.API.models.Login
import com.example.API.repositories.AuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/auth")
class AuthController {
    @Autowired
    lateinit var authRepository: AuthRepository

    @PostMapping("/login")
    fun login(@RequestBody login: Login) {
        return authRepository.login(login)
    }
}