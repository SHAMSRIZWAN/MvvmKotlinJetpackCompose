package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.others.MenuItem
import com.example.mvvmKotlinJetpackCompose.data.repos.DashboardRepository
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModelUseCase
import com.example.mvvmKotlinJetpackCompose.util.SingleEvent
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    dashboardUseCase: DashboardUseCase
) : BaseViewModelUseCase<DashboardUseCase>(dashboardUseCase) {

    @VisibleForTesting(otherwise = PRIVATE)
    val dashboardDataPrivate = MutableLiveData<Resource<DashboardResponse>>()
    val dashboardData: LiveData<Resource<DashboardResponse>> get() = dashboardDataPrivate


    @VisibleForTesting(otherwise = PRIVATE)
    val userIdDataPrivate = MutableLiveData<Resource<String>>()
    val userIdData: LiveData<Resource<String>> get() = userIdDataPrivate


    @VisibleForTesting(otherwise = PRIVATE)
    val logoutPrivate = MutableLiveData<SingleEvent<Resource<Boolean>>>()
    val logoutData: LiveData<SingleEvent<Resource<Boolean>>> get() = logoutPrivate

//    init {
//        getDashBoarData()
//    }

    fun getDashBoarData() {

        viewModelScope.launch(exceptionHandler) {
            showLoading()

            val dashboardData = getUseCase().getDashboardData()
            dashboardDataPrivate.value = dashboardData

            val userId = getUseCase().getUserId()
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

            getUseCase().logout()
            logoutPrivate.value = SingleEvent(Success(true))

        }

    }


}