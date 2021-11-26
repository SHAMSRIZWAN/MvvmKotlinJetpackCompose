package com.example.mvvmKotlinJetpackCompose.ui.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModel
import com.example.mvvmKotlinJetpackCompose.util.ENTER_EMAIL_ID
import com.example.mvvmKotlinJetpackCompose.util.ENTER_PASSWORD
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
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
                showMessageDialog(DataError(ENTER_EMAIL_ID))
            }
            password.isEmpty() -> {
                showMessageDialog(DataError(ENTER_PASSWORD))

            }
            else -> {
                showLoading()

                viewModelScope.launch(exceptionHandler) {

                    getRepo().login(email, password)
                        .flowOn(getAppDispatcher().io())
                        .collect { loginResult ->
                            hideLoading()

                            if (loginResult.data != null) {
                                loginResponsePrivate.value = loginResult
                            } else {
                                showMessageDialog(loginResult as DataError<String>)
                            }

                        }

                }



            }
        }

    }




}
