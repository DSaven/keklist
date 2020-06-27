package com.example.keklist.models

import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("id") val id: Int,
    @SerializedName("created") val created: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Int,
    @SerializedName("img") val img: String,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("user_name") val user_name: String,
    @SerializedName("category") val category: String
)