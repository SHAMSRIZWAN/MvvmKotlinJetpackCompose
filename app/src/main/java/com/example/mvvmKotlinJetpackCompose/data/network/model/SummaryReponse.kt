package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SummaryReponse(
    @Json(name = "data")
    val `data`: DataX=DataX(),
    @Json(name = "message")
    val message: String="",
    @Json(name = "status")
    val status: Boolean=false
)
@JsonClass(generateAdapter = true)
data class DataX(
    @Json(name = "Amount")
    val amount: Double=0.0,
    @Json(name = "liqrDeduction")
    val liqrDeduction: Double=0.0,
    @Json(name = "serviceCharge")
    val serviceCharge: Double=0.0,
    @Json(name = "totalDeduction")
    val totalDeduction: Double=0.0
)