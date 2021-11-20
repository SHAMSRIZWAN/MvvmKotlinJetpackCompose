package com.example.mvvmKotlinJetpackCompose.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DashboardResponse(
    @Json(name = "data")
    val `data`: Data = Data(),
    @Json(name = "message")
    val message: String = "",
    @Json(name = "status")
    val status: Boolean = false
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "Balance_INR")
        val balanceINR: Double = 0.0,
        @Json(name = "Balance_Liqr")
        val balanceLiqr: Double = 0.0,
        @Json(name = "Balance_USD")
        val balanceUSD: Double = 0.0,
        @Json(name = "LiqrToINR")
        val liqrToINR: Double = 0.0,
        @Json(name = "Redeem_Balance")
        val redeemBalance: Double = 0.0,
        @Json(name = "ServiceCharge")
        val serviceCharge: Double = 0.0
    )
}