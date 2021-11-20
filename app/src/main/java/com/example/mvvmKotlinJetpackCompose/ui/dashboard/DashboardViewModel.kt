package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import android.net.Uri
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
import com.example.mvvmKotlinJetpackCompose.util.coroutines.AppDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.net.URI
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(repo: DashboardRepo, appDispatcher: AppDispatcher) :
    BaseViewModel<DashboardRepo>(repo, appDispatcher) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val dashboardDataPrivate = MutableLiveData<Resource<DashboardResponse>>()
    val dashboardData: LiveData<Resource<DashboardResponse>> get() = dashboardDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val userIdDataPrivate = MutableLiveData<Resource<String>>()
    val userIdData: LiveData<Resource<String>> get() = userIdDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val logoutPrivate = MutableLiveData<SingleEvent<Resource<Boolean>>>()
    val logout: LiveData<SingleEvent<Resource<Boolean>>> get() = logoutPrivate

    init {
        viewModelScope.launch {
            showLoading()

            flow {
                emit(getRepo().getDashboardData())
            }.collect {
                hideLoading()
                dashboardDataPrivate.value = it
            }

        }

        viewModelScope.launch {

            flow {
                emit(getRepo().getUserId())
            }.collect {

                userIdDataPrivate.value = Success(it)
            }

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

        viewModelScope.launch {
            flow {
                emit(getRepo().logout())
            }.flowOn(getAppDispatcher().computation())
                .collect {
                    if (it.data == true) {
                        logoutPrivate.value = SingleEvent(Success(true))
                    }
                }


        }

    }


}