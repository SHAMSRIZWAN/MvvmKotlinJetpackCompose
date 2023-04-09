package com.example.mvvmKotlinJetpackCompose.ui.login

import com.example.mvvmKotlinJetpackCompose.BaseViewModelRepositoryTest
import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.model.LoginResponse
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.data.repos.LoginRepository
import com.example.mvvmKotlinJetpackCompose.util.ENTER_EMAIL_ID
import com.example.mvvmKotlinJetpackCompose.util.ENTER_PASSWORD
import com.example.mvvmKotlinJetpackCompose.util.NO_INTERNET_CONNECTION
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Test


class LoginViewModelTest : BaseViewModelRepositoryTest<LoginViewModel, LoginRepository>() {
    lateinit var apiHelper: ApiHelper
    lateinit var preferencesHelper: PreferencesHelper

    @ExperimentalCoroutinesApi
    override fun setUp() {
        apiHelper = mockk(relaxUnitFun = true)
        preferencesHelper = mockk(relaxUnitFun = true)
        repository = mockk()
        viewModelUnderTest =  spyk(LoginViewModel(repository))
    }

    @Test
    fun `onSignBtnClick , empty email and empty pass, return DataError ENTER_EMAIL_ID `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("", "")
        viewModelUnderTest.showMessageDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showMessageDialog.value?.errorDescription

        assertEquals(ENTER_EMAIL_ID, loginfail)


    }
    @Test
    fun `onSignBtnClick , empty email, return DataError ENTER_EMAIL_ID `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("", "pas")
        viewModelUnderTest.showMessageDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showMessageDialog.value?.errorDescription

        assertEquals(ENTER_EMAIL_ID, loginfail)


    }


    @Test
    fun `onSignBtnClick , empty pass, return DataError ENTER_PASSWORD `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("emal", "")
        viewModelUnderTest.showMessageDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showMessageDialog.value?.errorDescription

        assertEquals(ENTER_PASSWORD, loginfail)


    }


    @Test
    fun `onSignBtnClick,filled email and password,return success`() {
        //Given
       coEvery {
            repository.login(any(), any())
        } returns flow { emit(testDataClassGenerator.getSuccessLoginResponse()) }


        //when
        viewModelUnderTest.onSignInBtnClick("username@gmail.com", "password@123")
        viewModelUnderTest.loginResponsePrivate.observeForever {}

        //then
        val loginResponse = viewModelUnderTest.loginResponsePrivate.value!!.data

        val expectedResponse = testDataClassGenerator.getSuccessLoginResponse().data


        assertEquals(expectedResponse, loginResponse)

        verify {
            repository.login(any(),any())
            viewModelUnderTest.showLoading()
            viewModelUnderTest.hideLoading()
        }

    }

    @Test
    fun `onSignBtnClick, Data Error No network,assert showErrorDialog`() {
        //Given
        coEvery {
            repository.login(any(), any())
        } returns flow { emit(testDataClassGenerator.getNoNetworkError() as Resource<LoginResponse>) }


        //when
        viewModelUnderTest.onSignInBtnClick("username@gmail.com", "password@123")
        viewModelUnderTest.loginResponsePrivate.observeForever {}

        //then
        val result = viewModelUnderTest.showMessageDialog.value

        assertEquals(NO_INTERNET_CONNECTION, result!!.errorDescription)

        verify {
            repository.login(any(),any())
            viewModelUnderTest.showLoading()
            viewModelUnderTest.hideLoading()
        }

    }


    @Test
    fun `onSignBtnClick, Data Error Exception,assert showErrorDialog `() {
        //Given
        every {
            repository.login(any(), any())
        } returns flow { emit(testDataClassGenerator.getNoNetworkError() as Resource<LoginResponse>) }


        //when
        viewModelUnderTest.onSignInBtnClick("username@gmail.com", "password@123")
        viewModelUnderTest.loginResponsePrivate.observeForever {}

        //then
        val result = viewModelUnderTest.showMessageDialog.value

        assertEquals(NO_INTERNET_CONNECTION, result!!.errorDescription)

        verify {
            repository.login(any(),any())
            viewModelUnderTest.showLoading()
            viewModelUnderTest.hideLoading()
        }

    }




}