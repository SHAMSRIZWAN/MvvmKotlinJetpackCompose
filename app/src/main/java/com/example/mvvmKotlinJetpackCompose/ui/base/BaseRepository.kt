package com.example.mvvmKotlinJetpackCompose.ui.base

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode


open class BaseRepository(
    private val apiHelper: ApiHelper,
    private val preferencesHelper: PreferencesHelper
) {


    fun getApiHelper(): ApiHelper {

        return apiHelper
    }

    fun getPreferencesHelper(): PreferencesHelper {
        return preferencesHelper
    }



    fun setAccessToken(accessToken: String) {
        getPreferencesHelper().setAccessToken(accessToken)
        getApiHelper().getApiHeader()?.protectedApiHeader?.accessToken = accessToken

    }

    fun setUserAsLoggedOut() {
        updateUserInfo(
            null,
            null,
            LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
            null,
            null,
            null
        )
    }

    fun updateUserInfo(
        accessToken: String?,
        userId: String?,
        loggedInMode: LoggedInMode,
        userName: String?,
        email: String?,
        profilePicPath: String?
    ) {
        getPreferencesHelper().setAccessToken(accessToken)

        getPreferencesHelper().setCurrentUserId(userId ?:"-1")
        getPreferencesHelper().setCurrentUserLoggedInMode(loggedInMode)
        getPreferencesHelper().setCurrentUserName(userName)
        getPreferencesHelper().setCurrentUserEmail(email)
        getPreferencesHelper().setCurrentUserProfilePicUrl(profilePicPath)

    }

    fun updateApiHeader(userId: String, accessToken: String) {

        getApiHelper().getApiHeader()?.protectedApiHeader?.userId=userId
        getApiHelper().getApiHeader()?.protectedApiHeader?.accessToken=accessToken
    }





}