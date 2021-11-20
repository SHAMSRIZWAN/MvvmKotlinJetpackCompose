package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PayResponse(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Boolean
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "amount_INR")
        val amountINR: Double,
        @Json(name = "amount_Liqr")
        val amountLiqr: Double,
        @Json(name = "payment_mode")
        val paymentMode: String,
        @Json(name = "rec_upi")
        val recUpi: String,
        @Json(name = "service_charge")
        val serviceCharge: Double,
        @Json(name = "total_deduction")
        val totalDeduction: Double,
        @Json(name = "trans_status")
        val transStatus: Int,
        @Json(name = "trans_status_desc")
        val transStatusDesc: String
    )
}