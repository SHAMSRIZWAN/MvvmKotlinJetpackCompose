package com.example.mvvmKotlinJetpackCompose.data.network

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.Data
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.util.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
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
            accessToken = token
        }

    }


    override fun login(email: String, password: String): Resource<LoginResponse> {
//        val service = serviceGenerator.getService()
//
//        val request = LoginRequest(email, password, "", "")
//
//        return when (val responseBodyPojo = processCall { service.login(request) }
//        ) {
//            is LoginResponse -> Success(data = responseBodyPojo)
//
//            else -> DataError(responseBodyPojo as String)
//        }
        val data = Data(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InN1b3JpendhbnN" +
                    "heXllZDc4NkBnbWFpbC5jb20iLCJuYmYiOjE2Mzc2NTE1MjMsImV4cCI6MTY0NTQyNzUyMywiaWF0" +
                    "IjoxNjM3NjUxNTIzfQ.qKA55avNfOJMU3lHE-e88jfAVwE_T7E12cbCwXAfYAU",
            "suorizwansayyed786@gmail.com", "User"
        )
        return Success(LoginResponse(data, "User login successful", true))

    }


    override suspend fun getDashboardData(): Resource<DashboardResponse> {
        delay(3000)

//        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader
//        val service = serviceGenerator.getService()
//
//        return when (val response = processCall { service.getDashboardData() }) {
//            is DashboardResponse -> Success(response)
//            else -> DataError(response as String)
//        }
        val data = DashboardResponse.Data(
            balanceINR = 6502.50, balanceLiqr = 260.10,
            balanceUSD = 28.61, liqrToINR = 25.00, serviceCharge = 15.0, redeemBalance = 65.025000,
        )
        return Success(DashboardResponse(data, "success", true))
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public inline fun processCall(responseCall: () -> Response<*>): Any? {
        if (!NetworkUtils.isNetworkAvailable(context)) {

            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                getResponseCodeString(responseCode)
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    public fun getResponseCodeString(responseCode: Int): String {
        if (responseCode in 400..499) {
            return CLIENT_SIDE_ERROR
        } else if (responseCode in 500..599) {
            return SERVER_SIDE_ERROR
        }
        return SOMETHING_WENT_WRONG
    }

}