package com.example.API.models

data class Auth (
    val rol_name: String,
    val user_id: Int,
    val user_email: String,
    val user_password: String
) {
}