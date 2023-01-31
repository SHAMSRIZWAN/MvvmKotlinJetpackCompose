package com.example.mvvmKotlinJetpackCompose.di.login

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.login.LoginRepo
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class LoginComponentManager @Inject constructor(
    userComponentProvider: Provider<LoginComponentBuilder>,
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) {


    var loginComponent: LoginComponent? = null
        private set

    init {
        val user = LoginRepo(apiHelper,preferencesHelper)
        loginComponent = userComponentProvider.get().bindLoginRepo(user).build()
    }


     fun destroyLoginComponent() {
        loginComponent = null
    }
 }


