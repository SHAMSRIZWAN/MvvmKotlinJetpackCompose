package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.PayResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.SendOtpResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.SummaryReponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import javax.inject.Inject

class DashboardRepo @Inject constructor(
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) :
    BaseRepository(apiHelper, preferencesHelper) {

    fun logout(): Resource<Boolean> {
        setUserAsLoggedOut()
        return Success(true)
    }

    suspend fun getSummary(upi: String, amount: String): Resource<SummaryReponse> {
        return getApiHelper().getSummary(upi, amount)

    }

    suspend fun sendOtp(pay: String): Resource<SendOtpResponse> {

        return getApiHelper().sendOtp(pay)
    }

    suspend fun pay(
        recName: String = "",
        recMobile: String = "",
        recUpi: String,
        amount: String,
        otp: String,
        otpToken: String,
    ): Resource<PayResponse> {

        return getApiHelper().pay(recName, recMobile, recUpi, amount, otp, otpToken)
    }

    suspend fun getDashboardData(): Resource<DashboardResponse> {

        return getApiHelper().getDashboardData()

    }

    fun getUserId(): String {

        return getPreferencesHelper().getCurrentUserId() ?:""
    }


}