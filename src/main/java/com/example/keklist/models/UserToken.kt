package com.example.keklist.models

import com.google.gson.annotations.SerializedName

data class UserToken(
    @SerializedName("status") val status: String,
    @SerializedName("token") val token: String
)