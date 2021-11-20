package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "userId")
    val userId: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "ipAddress")
    val ipAddress: String,
    @Json(name = "url")
    val url: String,

    )