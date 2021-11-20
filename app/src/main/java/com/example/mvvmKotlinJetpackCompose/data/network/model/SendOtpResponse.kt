package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendOtpResponse(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Boolean
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "otp_token")
        val otpToken: String
    )
}