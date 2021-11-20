package com.example.mvvmKotlinJetpackCompose.ui.scandetail

import com.example.mvvmKotlinJetpackCompose.BaseTest
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.error.ENTER_EMAIL_ID
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.DashboardRepo
import com.example.mvvmKotlinJetpackCompose.util.coroutines.TestDataClassGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert

import org.junit.Before
import org.junit.Test

class ScannedDetailsViewModelTest : BaseTest<ScannedDetailsViewModel, DashboardRepo>() {


    @ExperimentalCoroutinesApi
    @Before
    override fun setUp() {
        repository = mockk()
        viewModelUnderTest = ScannedDetailsViewModel(repository, testAppDispatcher)
    }


    @Test
    fun `onNextClick successResponse veryLiveData`() {

        coEvery { repository.getSummary("", "") } returns
                Success(TestDataClassGenerator().getSummaryResponse())

        viewModelUnderTest.onBtnClick("eewew", "pas", "btnText",9)
        viewModelUnderTest.summaryData.observeForever{}

        //then
        val loginfail=viewModelUnderTest.summaryData.value?.data
        println(loginfail.toString())

        Assert.assertEquals(ENTER_EMAIL_ID, loginfail)


    }


}