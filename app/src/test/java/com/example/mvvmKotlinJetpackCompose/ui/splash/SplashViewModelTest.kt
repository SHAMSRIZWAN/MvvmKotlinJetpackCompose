package com.example.mvvmKotlinJetpackCompose.ui.splash

import com.example.mvvmKotlinJetpackCompose.BaseTest
import com.example.mvvmKotlinJetpackCompose.ui.login.RegistrationRepo
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class SplashViewModelTest : BaseTest<SplashViewModel, RegistrationRepo>() {


    @ExperimentalCoroutinesApi
    @Before
    override fun setUp() {
        repository = mockk()
            viewModelUnderTest = SplashViewModel(repository, testAppDispatcher)
        testDataClassGenerator.getSummaryResponse()

    }

    //Test names must be  like subjectUnderTest_actionOrInput_resultState
    //Be creative when writing test cases, handle what might go wrong with the code handle other exception as well
    //like no internet or if api return failed state

    @ExperimentalCoroutinesApi
    @Test
    fun `decideActivity, input loggedOut state ,return value 2`() {
        runBlockingTest {
            //Given
            coEvery { repository.isUserLoggedIn() }returns LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
            //when
            viewModelUnderTest.decideActivity()
            viewModelUnderTest.singleEventOpenActivity.observeForever{}//other wise value will not be updated in livedata

            //Then
            val result= viewModelUnderTest.singleEventOpenActivity.value?.getContentIfNotHandled()?.data

            assertEquals( 2 , result)

        }

    }


    @ExperimentalCoroutinesApi
    @Test
    fun `decideActivity ,input loggedIn state , return value 1`() {
        runBlockingTest {
            //Given
            coEvery { repository.isUserLoggedIn() }returns LoggedInMode.LOGGED_IN_MODE_SERVER.type
            //when
            viewModelUnderTest.decideActivity()
            viewModelUnderTest.singleEventOpenActivity.observeForever{}//other wise value will not be updated in livedata

            //Then
            val result= viewModelUnderTest.singleEventOpenActivity.value?.getContentIfNotHandled()?.data

            assertEquals( 1 , result)

        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `decideActivity ,not logged in ,return value 2`() {
        runBlockingTest {
            //Given
            coEvery { repository.isUserLoggedIn() }returns LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
            //when
            viewModelUnderTest.decideActivity()
            viewModelUnderTest.singleEventOpenActivity.observeForever{}//other wise value will not be updated in livedata

            //Then
            val result= viewModelUnderTest.singleEventOpenActivity.value?.getContentIfNotHandled()?.data

            assertEquals( 2 , result)

        }

    }

//now runt all test at once

}