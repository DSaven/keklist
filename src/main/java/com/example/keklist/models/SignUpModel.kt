package com.example.keklist.models

data class SignUpModel(
    val name: String,
    val login: String,
    val password: String,
    val repeatPassword: String
)