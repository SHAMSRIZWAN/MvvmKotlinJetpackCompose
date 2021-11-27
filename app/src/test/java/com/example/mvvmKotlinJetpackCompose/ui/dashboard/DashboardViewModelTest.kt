package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import com.example.mvvmKotlinJetpackCompose.BaseTest
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.util.NO_INTERNET_CONNECTION
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class DashboardViewModelTest : BaseTest<DashboardViewModel, DashboardRepo>() {


    @ExperimentalCoroutinesApi
    override fun setUp() {
        repository = mockk(relaxUnitFun = true)
        viewModelUnderTest = DashboardViewModel(repository, appDispatcher)
    }


    @Test
    fun `dashboard,success full response,return success`() {

        //Given
        val response = testDataClassGenerator.getSuccessDashboardResponse();
        val userId="123"

        coEvery {
            repository.getDashboardData()
        } returns response
        every {
            repository.getUserId()
        }returns userId

        //when
        viewModelUnderTest.getDashBoarData()
        //then
        val result = viewModelUnderTest.dashboardDataPrivate.value!!.data
        val userIdActual = viewModelUnderTest.userIdDataPrivate.value!!.data
        assertEquals(response.data, result)
        assertEquals(userId, userIdActual)

    }




    @Test
    fun `on logout,logout method call,return success`() {

        //Given

        //when
        viewModelUnderTest.logout()

        //then
        val result = viewModelUnderTest.logoutPrivate.value!!.getContentIfNotHandled()!!.data
        assertEquals(true, result)

    }

}