package com.example.mvvmKotlinJetpackCompose

import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.util.NO_INTERNET_CONNECTION
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File


class TestDataClassGenerator {

    val moshi = Moshi.Builder().build()

    //generic function to  generate data classes from json file path
    private inline fun <reified T> buildDataClassFromJson(json: String): T {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        return jsonAdapter.fromJson(json)!!
    }



    fun getSuccessLoginResponse(): Resource<LoginResponse> {
        val jsonString = getJson("LoginApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }



    fun getFailedLoginResponse():Resource<LoginResponse> {
        val jsonString = getJson("LoginFailedApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }


    fun getSuccessDashboardResponse(): Resource<DashboardResponse> {
        val data = DashboardResponse.Data(
            balanceINR = 6502.50, balanceLiqr = 260.10,
            balanceUSD = 28.61, liqrToINR = 25.00, serviceCharge = 15.0, redeemBalance = 65.025000,
        )
        return Success(DashboardResponse(data, "success", true))
    }




    fun getNoNetworkError():Resource<Any> {

        return DataError(NO_INTERNET_CONNECTION)
    }


    private fun getJson(resourceName: String): String {
        val file = File("src/test/resources/$resourceName")

        return String(file.readBytes())
    }


}
