package com.example.mvvmKotlinJetpackCompose.ui.login

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.util.NO_INTERNET_CONNECTION
import com.example.mvvmKotlinJetpackCompose.TestDataClassGenerator
import com.example.mvvmKotlinJetpackCompose.data.repos.LoginRepository
import com.example.mvvmKotlinJetpackCompose.util.coroutines.TestDispatcherProvider
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest {


    lateinit var apiHelper: ApiHelper

    lateinit var preferencesHelper: PreferencesHelper

    lateinit var repoUnderTest: LoginRepository

    protected val testDataClassGenerator: TestDataClassGenerator = TestDataClassGenerator()

    @Before
    fun setTup() {
        apiHelper = mockk(relaxUnitFun = true)
        preferencesHelper = mockk(relaxUnitFun = true)
        val appDispatcher = TestDispatcherProvider()
        repoUnderTest = LoginRepository(appDispatcher,apiHelper, preferencesHelper)

    }

    @Test
    fun `login , success response,verify setUserLoggedIn and update token`() {

        //Given
        every {
            apiHelper.login(
                any(),
                any()
            )
        } returns testDataClassGenerator.getSuccessLoginResponse()


        //when
        var result: Resource<LoginResponse>? = null

        runBlocking  {
            result =  repoUnderTest.login("", "").first()
        }

        //then
        assertEquals(true, result!!.data!!.status)
        verify (exactly = 1){

            preferencesHelper.setUserLoggedIn(
                result!!.data!!.data.userId,
                result!!.data!!.data.userType,
                result!!.data!!.data.userId,
                result!!.data!!.data.token
            )

            apiHelper.updateToken(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `login ,response false,assert response msg but don't verify setUserLoggedIn and update token`() {

        //Given
        every {
            apiHelper.login(
                any(),
                any()
            )
        } returns testDataClassGenerator.getFailedLoginResponse()

        //when
        var result: Resource<LoginResponse>? = null

        runBlockingTest  {
            result =  repoUnderTest.login("", "").first()
        }

        //then
        assertEquals("User login Unsuccessful", result!!.errorDescription)

        verify(exactly = 0) {
            preferencesHelper.setUserLoggedIn(any(), any(), any(), any())
            apiHelper.updateToken(any())

        }


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `login , no network ,assert no network error description but don't verify setUserLoggedIn and update token`() {

        //Given
        every {
            apiHelper.login(
                any(),
                any()
            )
        } returns testDataClassGenerator.getNoNetworkError() as Resource<LoginResponse>


        //when
        var result: Resource<LoginResponse>? = null
        runBlocking  {
            result =  repoUnderTest.login("", "").first()
        }


        //then
        assertEquals(NO_INTERNET_CONNECTION, result!!.errorDescription)

        verify(exactly = 0) {
            preferencesHelper.setUserLoggedIn(any(), any(), any(), any())
            apiHelper.updateToken(any())

        }


    }

}