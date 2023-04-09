package com.example.mvvmKotlinJetpackCompose.data.network

import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse


interface ApiHelper {

    fun getApiHeader(): ApiHeader?
    fun updateToken(token: String)
    fun login(email: String, password: String): Resource<LoginResponse>

    suspend fun getDashboardData(): Resource<DashboardResponse>
}
