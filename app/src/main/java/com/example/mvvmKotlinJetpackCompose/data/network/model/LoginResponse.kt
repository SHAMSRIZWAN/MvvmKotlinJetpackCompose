package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "data")
    var `data`: Data=Data("","",""),
    @Json(name = "message")
    var message: String,
    @Json(name = "status")
    var status: Boolean
)