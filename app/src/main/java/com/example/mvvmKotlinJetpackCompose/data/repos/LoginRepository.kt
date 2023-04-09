package com.example.mvvmKotlinJetpackCompose.data.repos

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.di.login.LoginScope
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.util.coroutines.AppDispatcherProvider
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@LoginScope
class LoginRepository @Inject constructor(
    appDispatcherProvider: DispatcherProvider,
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) : BaseRepository(appDispatcherProvider,apiHelper, preferencesHelper) {


    fun login(email: String, password: String): Flow<Resource<LoginResponse>> {
        val loginResult = getApiHelper().login(email, password)

        loginResult.data?.let {
            if (it.status) {
                val loginResponse = loginResult.data

                if (loginResponse.status) {
                    val userId = loginResponse.data.userId
                    val token = loginResponse.data.token
                    val userType = loginResponse.data.userType

                    getPreferencesHelper().setUserLoggedIn(
                        userId = userId,
                        userName = userType,
                        email = userId, accessToken = token
                    )
                    getApiHelper().updateToken(token)

                }
            } else {
                return flow {
                    emit(DataError(loginResult.data.message))
                }
            }
        }

        return flow {
            emit(loginResult)
        }
    }

    suspend fun isUserLoggedIn(): Flow<Int?> {

        return flow {
            delay(3000)

            emit(getPreferencesHelper().getCurrentUserLoggedInMode())
        }.flowOn(getAppDispatcher().computation())

    }



}