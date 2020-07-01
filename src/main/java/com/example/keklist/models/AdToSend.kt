package com.example.keklist.models

data class AdToSend(
    val title: String,
    val category_id: Int,
    val description: String,
    val price: Int,
    val img: String,
    val phone_number: String
)