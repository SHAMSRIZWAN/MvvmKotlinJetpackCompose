package com.example.mvvmKotlinJetpackCompose.util.coroutines

import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.util.NO_INTERNET_CONNECTION
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File


class TestDataClassGenerator {

    val moshi = Moshi.Builder().build()

    //generic function to  generate data classes from json file path
    inline fun <reified T> buildDataClassFromJson(json: String): T {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        val result = jsonAdapter.fromJson(json)!!
        return result
    }


    fun getSuccessFlowLoginResponse(): Resource<LoginResponse> {
        val jsonString = getJson("LoginApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }

    fun getSuccessLoginResponse(): Resource<LoginResponse> {
        val jsonString = getJson("LoginApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }



    fun getFailedLoginResponse():Resource<LoginResponse> {
        val jsonString = getJson("LoginFailedApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }

    fun getNoNetworkError():Resource<Any> {

        return DataError(NO_INTERNET_CONNECTION)
    }


    private fun getJson(resourceName: String): String {
        val file = File("src/test/resources/$resourceName")

        return String(file.readBytes())
    }


}
