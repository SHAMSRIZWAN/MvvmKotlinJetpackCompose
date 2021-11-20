package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "token")
    var token: String,
    @Json(name = "userId")
    var userId: String,
    @Json(name = "userType")
    var userType: String
)