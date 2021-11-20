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
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getCurrentUserName(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(userName: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) {
        mPrefs.edit()
            .putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl)
            .apply()
    }

    override suspend fun getCurrentUserLoggedInMode(): Int {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
            LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)
    }

    override fun setCurrentUserLoggedInMode(mode: LoggedInMode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.type)
            .apply()
    }

    override fun getAccessToken(): String? {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }
}