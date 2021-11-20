package com.example.mvvmKotlinJetpackCompose.util.coroutines

import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.SummaryReponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File


class TestDataClassGenerator {

    val moshi = Moshi.Builder().build()

    //generic function to  generate data classes from json file path
 inline fun <reified T> buildDataClassFromJson(json: String): T {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter<T>(T::class.java)
        val result = jsonAdapter.fromJson(json)!!


        return result
    }



    fun getSummaryResponse(): SummaryReponse {
        val jsonString = getJson("SummaryApiResponse.json")

        return buildDataClassFromJson(jsonString)
    }

    fun getLoginResponse(): Resource<LoginResponse> {
        val jsonString = getJson("LoginApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }


    private fun getJson(resourceName: String): String {
        val file = File("src/test/resources/"+resourceName)

        return String(file.readBytes())
    }

    
}
