
package com.example.mvvmKotlinJetpackCompose.data.network

import com.example.mvvmKotlinJetpackCompose.di.ApiInfo
import javax.inject.Inject


class ApiHeader @Inject constructor(val publicApiHeader: PublicApiHeader, val protectedApiHeader: ProtectedApiHeader) {

    class PublicApiHeader  @Inject constructor( @ApiInfo var apiKey: String)

    class ProtectedApiHeader  (@ApiInfo var apiKey: String, var userId: String?, var accessToken: String?)
}