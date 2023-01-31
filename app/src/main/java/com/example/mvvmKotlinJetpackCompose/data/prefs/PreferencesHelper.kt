package com.example.mvvmKotlinJetpackCompose.data.prefs

import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode


interface   PreferencesHelper {

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
    fun setUserLoggedIn(
        userId: String,
        userName: String,
        email: String,
        accessToken: String,
        profile: String = ""
    )

    fun updateUserInfo(
        accessToken: String?,
        userId: String?,
        loggedInMode: LoggedInMode,
        userName: String?,
        email: String?,
        profilePicPath: String?)

    fun clearAll()

}