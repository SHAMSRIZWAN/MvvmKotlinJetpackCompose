package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.others.MenuItem
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModel
import com.example.mvvmKotlinJetpackCompose.util.SingleEvent
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    repo: DashboardRepo,
    appDispatcher: DispatcherProvider
) :
    BaseViewModel<DashboardRepo>(repo, appDispatcher) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val dashboardDataPrivate = MutableLiveData<Resource<DashboardResponse>>()
    val dashboardData: LiveData<Resource<DashboardResponse>> get() = dashboardDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val userIdDataPrivate = MutableLiveData<Resource<String>>()
    val userIdData: LiveData<Resource<String>> get() = userIdDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val logoutPrivate = MutableLiveData<SingleEvent<Resource<Boolean>>>()
    val logoutData: LiveData<SingleEvent<Resource<Boolean>>> get() = logoutPrivate

    init {

        getDashBoarData()


    }

     fun getDashBoarData() {

        viewModelScope.launch(exceptionHandler) {
            showLoading()

            val dashboardData = getRepo().getDashboardData()
            dashboardDataPrivate.value = dashboardData

            val userId = getRepo().getUserId()
            userIdDataPrivate.value = Success(userId)

            hideLoading()

        }
    }


    fun getMenuData(): List<MenuItem> = listOf(
        MenuItem(R.drawable.ic_prepaid, "Prepaid"),
        MenuItem(R.drawable.ic_postpaid, "PostPaid"),
        MenuItem(R.drawable.ic_dtf, "Dth"),
        MenuItem(R.drawable.ic_datacard, "DataCard"),
        MenuItem(R.drawable.ic_gas, "Gas"),
        MenuItem(R.drawable.ic_water, "Water"),
        MenuItem(R.drawable.ic_electricity, "Electricity"),
        MenuItem(R.drawable.ic_billpayment, "Bill Payment"),
        MenuItem(R.drawable.ic_fund_recieve, "Fun receive"),
        MenuItem(R.drawable.ic_bus, "bus"),
        MenuItem(R.drawable.ic_flight, "flight"),
        MenuItem(R.drawable.ic_hotel_booking, "Hotel booking"),
    )


    fun logout() {

        viewModelScope.launch(exceptionHandler) {
            flow {
                emit(getRepo().logout())
            }.flowOn(getAppDispatcher().computation())
                .collect {

                    logoutPrivate.value = SingleEvent(Success(true))

                }


        }

    }


}