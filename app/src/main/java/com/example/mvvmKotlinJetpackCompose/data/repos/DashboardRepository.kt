package com.example.mvvmKotlinJetpackCompose.data.repos

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    appDispatcher: DispatcherProvider,
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) : BaseRepository(appDispatcher,apiHelper, preferencesHelper) {

    fun logout() {

        flow<Unit> {
            setUserAsLoggedOut()
        }.flowOn(getAppDispatcher().computation())

    }

    suspend fun getDashboardData(): Resource<DashboardResponse> {

        return getApiHelper().getDashboardData()

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