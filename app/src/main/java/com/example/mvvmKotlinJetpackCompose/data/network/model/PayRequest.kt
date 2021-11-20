package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PayRequest(
    @Json(name = "amount_inr")
    val amountInr: Int,
    @Json(name = "otp")
    val otp: String,
    @Json(name = "otp_token")
    val otpToken: String,
    @Json(name = "rec_mobile")
    val recMobile: String,
    @Json(name = "rec_name")
    val recName: String,
    @Json(name = "rec_upi")
    val recUpi: String
)