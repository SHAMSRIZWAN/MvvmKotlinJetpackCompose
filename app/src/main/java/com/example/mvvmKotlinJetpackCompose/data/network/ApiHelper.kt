package com.example.mvvmKotlinJetpackCompose.data.network

import com.example.mvvmKotlinJetpackCompose.data.network.model.*


interface ApiHelper {

    fun getApiHeader(): ApiHeader?
    fun updateToken(token: String);
    suspend fun login(email: String, password: String): Resource<LoginResponse>

    suspend fun getSummary(upi: String, amount: String): Resource<SummaryReponse>
    suspend fun sendOtp(pay: String): Resource<SendOtpResponse>
    suspend fun pay(
        recName: String,
        recMobile: String,
        recUpi: String,
        amount: String,
        otp: String,
        otpToken: String,
    ): Resource<PayResponse>

   suspend fun getDashboardData(): Resource<DashboardResponse>
}
