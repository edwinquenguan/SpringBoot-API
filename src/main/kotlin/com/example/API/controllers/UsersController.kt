package com.example.API.controllers

import com.example.API.models.User
import com.example.API.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController {
    @Autowired
    lateinit var userRepository: UsersRepository

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userRepository.getUsers()
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): Map<String, Any>{

        val createdUser = userRepository.createUser(user)

        return if (createdUser > 0 ) {
            mapOf(
                "success" to true,
                "message" to "Usuario creado correctamente"
            )
        } else {
            mapOf(
                "success" to false,
                "message" to "No se pudo crear el usuario"
            )
        }
    }
}