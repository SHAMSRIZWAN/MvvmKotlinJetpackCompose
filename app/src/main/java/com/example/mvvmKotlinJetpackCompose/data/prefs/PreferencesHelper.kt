package com.example.mvvmKotlinJetpackCompose.data.prefs

import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode


 interface PreferencesHelper {

    suspend fun getCurrentUserLoggedInMode(): Int?

    fun setCurrentUserLoggedInMode(mode: LoggedInMode)

    fun getCurrentUserId(): String?

    fun setCurrentUserId(userId: String?)

    fun getCurrentUserName(): String?

    fun setCurrentUserName(userName: String?)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(email: String?)

    fun getCurrentUserProfilePicUrl(): String?

    fun setCurrentUserProfilePicUrl(profilePicUrl: String?)

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)



}