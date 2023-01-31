package com.example.mvvmKotlinJetpackCompose.di

import com.example.mvvmKotlinJetpackCompose.BuildConfig
import com.example.mvvmKotlinJetpackCompose.data.network.*
import com.example.mvvmKotlinJetpackCompose.data.network.moshiFactories.MyKotlinJsonAdapterFactory
import com.example.mvvmKotlinJetpackCompose.data.network.moshiFactories.MyStandardJsonAdapters
import com.squareup.moshi.Moshi
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class ExternalLibAppModule {

   @Provides
   @Singleton
   fun provideHeaderInterceptor(protectedApiHeader : ApiHeader.ProtectedApiHeader): Interceptor{
      return Interceptor { chain ->
         val original = chain.request()
         val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .header("Authorization", "Bearer " + protectedApiHeader.accessToken)
            .method(original.method, original.body)
            .build()

         chain.proceed(request)
      }

   }

   @Provides
   @Singleton
   fun provideLoggingInterceptor(): HttpLoggingInterceptor {
     return HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
     }
   }


   @Provides
   @Singleton
   fun provideRetrofitClient(headerInterceptor : Interceptor,loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient.Builder{

     val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
      okHttpBuilder.addInterceptor(headerInterceptor)
      okHttpBuilder.addInterceptor(loggingInterceptor)
      okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
      okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
      return okHttpBuilder

   }

    @Provides
    @Singleton
   fun provideRetrofit(okHttpBuilder: OkHttpClient.Builder, moshi: Moshi):Retrofit{
      return Retrofit.Builder()
         .baseUrl(baseUrl)
         .client(okHttpBuilder.build())
         .addConverterFactory(MoshiConverterFactory.create(moshi))
         .build()
   }

    @Provides
    @Singleton
    fun  provideService(retrofit: Retrofit): Service {
        return  retrofit.create(Service::class.java)
    }


    @Provides
   @Singleton
   fun provideMoshi():Moshi{
    return  Moshi.Builder()
         .add(MyStandardJsonAdapters.FACTORY)
         .add(MyKotlinJsonAdapterFactory()).build()
   }





}