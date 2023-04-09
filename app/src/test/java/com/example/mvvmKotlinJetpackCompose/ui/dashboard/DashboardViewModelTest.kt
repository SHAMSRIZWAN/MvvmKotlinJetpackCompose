package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmKotlinJetpackCompose.BaseViewModelRepositoryTest
import com.example.mvvmKotlinJetpackCompose.BaseViewModelUseCaseTest
import com.example.mvvmKotlinJetpackCompose.TestDataClassGenerator
import com.example.mvvmKotlinJetpackCompose.data.repos.DashboardRepository
import com.example.mvvmKotlinJetpackCompose.util.coroutines.CoroutineTestRule
import com.example.mvvmKotlinJetpackCompose.util.coroutines.TestDispatcherProvider
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardViewModelTest {
    protected val testDataClassGenerator: TestDataClassGenerator = TestDataClassGenerator()

    lateinit var viewModelUnderTest: DashboardViewModel

    lateinit var dashboardUseCase: DashboardUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    open val mainCoroutineRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        dashboardUseCase = mockk<DashboardUseCase>()
        viewModelUnderTest = DashboardViewModel(dashboardUseCase)
    }


    @Test
    fun `dashboard,success full response,return success`() = runBlocking{

        //Given
        val response = testDataClassGenerator.getSuccessDashboardResponse();
        val userId = "123"

        coEvery {
            dashboardUseCase.getDashboardData()
        } returns response
        every {
            dashboardUseCase.getUserId()
        } returns userId

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
        justRun  {
            dashboardUseCase.logout()
        }
        //when
        viewModelUnderTest.logout()

        //then
        val result = viewModelUnderTest.logoutPrivate.value!!.getContentIfNotHandled()!!.data
        assertEquals(true, result)

    }

}