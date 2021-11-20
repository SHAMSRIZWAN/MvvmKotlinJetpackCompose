package com.example.mvvmKotlinJetpackCompose.data.network

import android.content.Context
import com.example.mvvmKotlinJetpackCompose.data.network.model.*
import com.example.mvvmKotlinJetpackCompose.error.NETWORK_ERROR
import com.example.mvvmKotlinJetpackCompose.error.NO_INTERNET_CONNECTION
import com.example.mvvmKotlinJetpackCompose.util.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class AppApiHelper @Inject constructor(
    @ApplicationContext val context: Context,
    private val apiHeader: ApiHeader,
) : ApiHelper {

    @Inject
    lateinit var serviceGenerator: ServiceGenerator

    override fun getApiHeader(): ApiHeader {

        return apiHeader
    }

    override fun updateToken(token: String) {
        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader.apply {
            accessToken=token
        }

    }


    override suspend fun login(email: String, password: String): Resource<LoginResponse> {
        val service = serviceGenerator.createService(Service::class.java)

        val request = LoginRequest(email, password, "", "")

        return when (val responseBodyPojo = processCall { service.login(request) }

        ) {
            is LoginResponse -> Success(data = responseBodyPojo)

            else -> DataError(responseBodyPojo as String)
        }


    }

    override suspend fun getSummary(upi: String, amount: String): Resource<SummaryReponse> {


        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader

        val service = serviceGenerator.createService(Service::class.java)

        return when (val response = processCall { service.getSummary(amount) }) {
            is SummaryReponse -> Success(data = response)

            else -> DataError(response as String)
        }

    }

    override suspend fun sendOtp(pay: String): Resource<SendOtpResponse> {
        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader
        val service = serviceGenerator.createService(Service::class.java)

        return when (val response = processCall { service.sendOtp(pay) }) {
            is SendOtpResponse -> Success(data = response)
            else -> DataError(response as String)
        }
    }

    override suspend fun pay(
        recName: String,
        recMobile: String,
        recUpi: String,
        amount: String,
        otp: String,
        otpToken: String,
    ): Resource<PayResponse> {

        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader
        val service = serviceGenerator.createService(Service::class.java)
        val payRequest =
            PayRequest(Integer.parseInt(amount), otp, otpToken, recMobile, recName, recUpi)

        return when (val response = processCall {
            service.pay(payRequest)
        }) {
            is PayResponse -> Success(data = response)
            else -> DataError(response as String)
        }
    }

    override suspend fun getDashboardData(): Resource<DashboardResponse> {

        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader
        val service = serviceGenerator.createService(Service::class.java)

        return when (val response = processCall { service.getDashboardData() }) {
            is DashboardResponse -> Success(response)
            else -> DataError(response as String)
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!NetworkUtils.isNetworkAvailable(context)) {

            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode.toString()
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

}