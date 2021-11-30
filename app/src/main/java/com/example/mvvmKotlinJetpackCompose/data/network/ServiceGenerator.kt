package com.example.mvvmKotlinJetpackCompose.data.network

import com.example.mvvmKotlinJetpackCompose.BuildConfig
import com.example.mvvmKotlinJetpackCompose.data.network.moshiFactories.MyKotlinJsonAdapterFactory
import com.example.mvvmKotlinJetpackCompose.data.network.moshiFactories.MyStandardJsonAdapters
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 30
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30
private const val baseUrl="https://your.com/Api/"

@Singleton
class ServiceGenerator  @Inject constructor(){

    private var retrofit: Retrofit
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    var protectedApiHeader:ApiHeader.ProtectedApiHeader= ApiHeader.ProtectedApiHeader("","","")

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .header("Authorization", "Bearer " + protectedApiHeader.accessToken)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
      get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    var client: OkHttpClient


    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()

    }

    fun  getService(): Service {
       return  retrofit.create(Service::class.java)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(MyStandardJsonAdapters.FACTORY)
            .add(MyKotlinJsonAdapterFactory())

            .build()
    }

}