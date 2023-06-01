package com.example.scanner.data.model

data class UserDTO(
    val name: String,
    val password: String,
    val email: String = ""
)
