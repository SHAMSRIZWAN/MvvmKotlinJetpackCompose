package com.example.mvvmKotlinJetpackCompose.ui.base

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper


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

    fun getUserId(): String {
        return getPreferencesHelper().getCurrentUserId() ?: ""

    }


}