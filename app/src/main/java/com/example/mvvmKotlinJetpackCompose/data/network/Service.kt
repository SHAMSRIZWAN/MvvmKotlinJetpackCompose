package com.example.mvvmKotlinJetpackCompose.data.network


import com.example.mvvmKotlinJetpackCompose.data.network.model.*
import retrofit2.Response
import retrofit2.http.*


interface Service {

    @POST("Account/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @GET("pay/summary")
    suspend fun getSummary( @Query("Amount") amount: String):Response<SummaryReponse>

    @GET("Account/sendOtp")
    suspend fun sendOtp( @Query("type") type: String):Response<SendOtpResponse>

    @POST("pay/upi")
    suspend fun pay(@Body request: PayRequest):Response<PayResponse>

    @GET("dashboard")
    suspend fun getDashboardData():Response<DashboardResponse>


}
