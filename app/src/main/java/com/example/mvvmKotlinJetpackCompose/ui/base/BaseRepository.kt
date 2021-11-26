package com.example.mvvmKotlinJetpackCompose.ui.base

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import kotlinx.coroutines.flow.flow


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

    fun getUserId(): kotlinx.coroutines.flow.Flow<String> {
        return flow { emit(getPreferencesHelper().getCurrentUserId() ?:"") }
    }





}