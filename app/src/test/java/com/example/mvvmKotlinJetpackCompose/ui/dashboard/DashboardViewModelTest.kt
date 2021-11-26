package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import com.example.mvvmKotlinJetpackCompose.BaseTest
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

class DashboardViewModelTest : BaseTest<DashboardViewModel, DashboardRepo>() {


    @ExperimentalCoroutinesApi
    override fun setUp() {
       repository= mockk()
        viewModelUnderTest= DashboardViewModel(repository,appDispatcher)
    }

    @Test
    fun `on logout,logout method call,return success`(){

        //Given
        every {
            repository.logout()
        }just runs

        //when
        viewModelUnderTest.logout()

        //then
        val result=viewModelUnderTest.logoutPrivate.value!!.getContentIfNotHandled()!!.data
        assertEquals(true, result)

    }

}