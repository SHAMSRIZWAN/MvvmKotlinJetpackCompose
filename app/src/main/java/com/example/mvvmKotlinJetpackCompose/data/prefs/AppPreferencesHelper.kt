package com.example.mvvmKotlinJetpackCompose.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmKotlinJetpackCompose.di.PreferenceInfo
import com.example.mvvmKotlinJetpackCompose.util.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
    @ApplicationContext val context: Context,
    @PreferenceInfo val prefFileName: String?,
) : PreferencesHelper {


    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);

    override fun getCurrentUserId(): String? {
        val userId =
            mPrefs.getString(PREF_KEY_CURRENT_USER_ID, NULL_INDEX)
        return if (userId == NULL_INDEX) null else userId
    }

    override fun setCurrentUserId(userId: String?) {
        val id = userId ?: NULL_INDEX
        mPrefs.putAny(PREF_KEY_CURRENT_USER_ID, id)

    }

    override fun getCurrentUserName(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(userName: String?) {
        mPrefs.putAny(PREF_KEY_CURRENT_USER_NAME, userName)

    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPrefs.putAny(PREF_KEY_CURRENT_USER_EMAIL, email)

    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) {

        mPrefs.putAny(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl)

    }

    override suspend fun getCurrentUserLoggedInMode(): Int {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE, LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)
    }

    override fun setCurrentUserLoggedInMode(mode: LoggedInMode) {
        mPrefs.putAny(PREF_KEY_USER_LOGGED_IN_MODE, mode.type)

    }

    override fun getAccessToken(): String? {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPrefs.putAny(PREF_KEY_ACCESS_TOKEN, accessToken)
    }


    override fun setUserLoggedIn(
        userId: String,
        userName: String,
        email: String,
        accessToken: String,
        profile: String
    ) {

       setCurrentUserId(userId)
       setCurrentUserName(userName)
        setCurrentUserEmail(email)
        setCurrentUserProfilePicUrl(profile)
        setAccessToken(accessToken)
       setCurrentUserLoggedInMode(LoggedInMode.LOGGED_IN_MODE_SERVER)
    }

    override fun updateUserInfo(
        accessToken: String?,
        userId: String?,
        loggedInMode: LoggedInMode,
        userName: String?,
        email: String?,
        profilePicPath: String?
    ) {
        setAccessToken(accessToken)
        setCurrentUserId(userId ?:"-1")
        setCurrentUserLoggedInMode(loggedInMode)
        setCurrentUserName(userName)
        setCurrentUserEmail(email)
        setCurrentUserProfilePicUrl(profilePicPath)

    }

    override fun clearAll() {
        mPrefs.edit().clear().apply()
    }

    private fun SharedPreferences.putAny(name: String, any: Any?) {
        when (any) {
            is String -> edit().putString(name, any).apply()
            is Int -> edit().putInt(name, any).apply()
            is Boolean -> edit().putBoolean(name,any).apply()
            is Float -> edit().putFloat(name,any).apply()
        }
    }

   private fun SharedPreferences.remove(name:String){
        edit().remove(name).apply()
    }
}