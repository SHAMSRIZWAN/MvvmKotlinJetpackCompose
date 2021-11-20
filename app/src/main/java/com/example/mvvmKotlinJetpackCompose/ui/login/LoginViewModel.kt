package com.example.mvvmKotlinJetpackCompose.ui.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.error.ENTER_EMAIL_ID
import com.example.mvvmKotlinJetpackCompose.error.ENTER_PASSWORD
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModel
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    registrationRepo: RegistrationRepo,
    appDispatcher: DispatcherProvider,
) : BaseViewModel<RegistrationRepo>(registrationRepo, appDispatcher) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val loginResponsePrivate = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: LiveData<Resource<LoginResponse>> get() = loginResponsePrivate


    fun onSignInBtnClick(email: String, password: String) {
        when {
            email.isEmpty() -> {
                showErrorDialog.value = DataError(ENTER_EMAIL_ID)
            }
            password.isEmpty() -> {
                showErrorDialog.value = DataError(ENTER_PASSWORD)
            }
            else -> {
                showLoading()
                viewModelScope.launch {

                    flow {
                        emit(getRepo().login(email, password))
                    }.flowOn(getAppDispatcher().io())
                        .map {
                            when (it) {
                                is DataError -> {
                                    showErrorDialog.postValue(DataError(it.errorDescription))
                                    return@map DataError(it.errorDescription)
                                }
                                is Success -> {
                                    val loginResponse = it.data
                                    if (loginResponse?.status == true) {
                                        val userId = loginResponse.data.userId
                                        val token = loginResponse.data.token
                                        val userType = loginResponse.data.userType

                                        getRepo().setUserLoggedIn(userId = userId,
                                            userName = userType,
                                            email = userId, accessToken = token)

                                        return@map it

                                    }else{

                                        showErrorDialog.postValue(DataError(loginResponse?.message!!))
                                        return@map DataError(loginResponse.message)

                                    }

                                }
                            }

                        }.flowOn(getAppDispatcher().computation())
                        .collect { dashboardResponse ->
                            hideLoading()
                            loginResponsePrivate.value = dashboardResponse


                        }

                }
            }
        }

    }


}