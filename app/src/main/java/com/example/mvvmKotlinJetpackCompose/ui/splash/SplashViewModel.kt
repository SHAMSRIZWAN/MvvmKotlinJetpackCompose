package com.example.mvvmKotlinJetpackCompose.ui.splash

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.*
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModel
import com.example.mvvmKotlinJetpackCompose.ui.login.RegistrationRepo
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import com.example.mvvmKotlinJetpackCompose.util.SingleEvent
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    registrationRepo: RegistrationRepo,
    appDispatcher: DispatcherProvider,
) : BaseViewModel<RegistrationRepo>(registrationRepo, appDispatcher) {

    @VisibleForTesting(otherwise = PRIVATE)
    val privateSingleEventOpenActivity =
        MutableLiveData<SingleEvent<Resource<Int>>>()// if we keep this private
    //then it will not be visible for test package, hence its public, annotation helped us to make our code more
    //readable

    val singleEventOpenActivity: LiveData<SingleEvent<Resource<Int>>> get() = privateSingleEventOpenActivity//activity
    //should observe livedata because live data is immutable you cannot set its value , in this way we restrict
    //activity to directly manipulate our live data ,activity should only observe the data and should not try to update
    //the data, doing this we reduced the dependency between our activity and view model , it becomes loosely coupled
    //we must try to make our classes communication loosely coupled , we can reuse our activity in another app
    //with minimal changes if it has a same design

    @FlowPreview
    fun decideActivity() {
        showLoading()

        viewModelScope.launch(exceptionHandler) {

            getRepo().isUserLoggedIn()
                .flowOn(getAppDispatcher().computation())
                .collect {
                    hideLoading()
                    if (it == LoggedInMode.LOGGED_IN_MODE_SERVER.type) {
                        privateSingleEventOpenActivity.value = SingleEvent(Success(1))

                    } else {
                        privateSingleEventOpenActivity.value = SingleEvent(Success(2))

                    }
                }



        }

    }


}