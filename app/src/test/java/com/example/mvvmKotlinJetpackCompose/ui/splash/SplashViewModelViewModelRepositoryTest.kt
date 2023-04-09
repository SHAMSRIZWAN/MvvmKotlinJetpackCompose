package com.example.mvvmKotlinJetpackCompose.ui.splash

import com.example.mvvmKotlinJetpackCompose.BaseViewModelRepositoryTest
import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.data.repos.LoginRepository
import com.example.mvvmKotlinJetpackCompose.util.LoggedInMode
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test

class SplashViewModelViewModelRepositoryTest : BaseViewModelRepositoryTest<SplashViewModel, LoginRepository>() {

    lateinit var apiHelper: ApiHelper
    lateinit var preferencesHelper: PreferencesHelper



    @ExperimentalCoroutinesApi
    @Before
    override fun setUp() {

        apiHelper = mockk(relaxUnitFun = true)
        preferencesHelper = mockk(relaxUnitFun = true)
        repository = mockk()

        viewModelUnderTest = SplashViewModel(repository)

    }

    //Test names must be  like subjectUnderTest_actionOrInput_resultState
    //Be creative when writing test cases, handle what might go wrong with the code handle other exception as well
    //like no internet or if api return failed state

    @ExperimentalCoroutinesApi
    @Test
    fun `decideActivity, input loggedOut state ,return value 2`() {
        //Given
        coEvery { repository.isUserLoggedIn() } returns flow { emit(LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type) }

        //when
        viewModelUnderTest.decideActivity()
        viewModelUnderTest.singleEventOpenActivity.observeForever {}//other wise value will not be updated in livedata

        //Then
        val result =
            viewModelUnderTest.singleEventOpenActivity.value?.getContentIfNotHandled()?.data

        assertEquals(2, result)


    }


    @ExperimentalCoroutinesApi
    @Test
    fun `decideActivity ,input loggedIn state , return value 1`() {
        //Given
        coEvery { repository.isUserLoggedIn() } returns flow { emit(LoggedInMode.LOGGED_IN_MODE_SERVER.type) }
        //when
        viewModelUnderTest.decideActivity()
        viewModelUnderTest.singleEventOpenActivity.observeForever {}//other wise value will not be updated in livedata

        //Then
        val result =
            viewModelUnderTest.singleEventOpenActivity.value?.getContentIfNotHandled()?.data

        assertEquals(1, result)


    }

    @ExperimentalCoroutinesApi
    @Test
    fun `decideActivity ,not logged in ,return value 2`() {
        //Given
        coEvery { repository.isUserLoggedIn() } returns flow { emit(LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type) }
        //when
        viewModelUnderTest.decideActivity()
        viewModelUnderTest.singleEventOpenActivity.observeForever {}//other wise value will not be updated in livedata

        //Then
        val result =
            viewModelUnderTest.singleEventOpenActivity.value?.getContentIfNotHandled()?.data

        assertEquals(2, result)


    }

//now runt all test at once

}