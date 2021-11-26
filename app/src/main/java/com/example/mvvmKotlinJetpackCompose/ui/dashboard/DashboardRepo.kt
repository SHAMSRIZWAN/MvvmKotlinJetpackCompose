package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRepo @Inject constructor(
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) : BaseRepository(apiHelper, preferencesHelper) {

    fun logout() {
        setUserAsLoggedOut()
    }

    suspend fun getDashboardData(): Flow<Resource<DashboardResponse>> {

        return flow {
            emit(getApiHelper().getDashboardData())
        }

    }


    private fun setUserAsLoggedOut() {
        getPreferencesHelper().updateUserInfo(
            null,
            null,
            LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
            null,
            null,
            null
        )
    }


}