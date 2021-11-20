package com.example.mvvmKotlinJetpackCompose.ui.login

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import javax.inject.Inject

class RegistrationRepo @Inject constructor(
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) :
    BaseRepository(apiHelper, preferencesHelper) {


    suspend fun login(email: String, password: String): Resource<LoginResponse> {

     return   getApiHelper().login(email,password)
    }

    fun  setUserLoginMode(loggedInMode: LoggedInMode) {
        getPreferencesHelper().setCurrentUserLoggedInMode(loggedInMode)
    }

    suspend fun isUserLoggedIn():Int?{
        return  getPreferencesHelper().getCurrentUserLoggedInMode()
    }

    fun setUserLoggedIn(userId:String,userName:String,email: String,accessToken:String,profile:String="") {

        getPreferencesHelper().setCurrentUserId(userId)
        getPreferencesHelper().setCurrentUserName(userName)
        getPreferencesHelper().setCurrentUserEmail(email)
        getPreferencesHelper().setCurrentUserProfilePicUrl(profile)
        getPreferencesHelper().setAccessToken(accessToken)
        getPreferencesHelper().setCurrentUserLoggedInMode(LoggedInMode.LOGGED_IN_MODE_SERVER)
        getApiHelper().updateToken(accessToken)
    }

}