package com.example.mvvmKotlinJetpackCompose.ui.login

import androidx.lifecycle.Observer
import com.example.mvvmKotlinJetpackCompose.BaseTest
import com.example.mvvmKotlinJetpackCompose.error.ENTER_EMAIL_ID
import com.example.mvvmKotlinJetpackCompose.error.ENTER_PASSWORD
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test


class LoginViewModelTest : BaseTest<LoginViewModel, RegistrationRepo>() {


    @ExperimentalCoroutinesApi
    override fun setUp() {
        repository = mockk()
        viewModelUnderTest = LoginViewModel(repository, testAppDispatcher)
    }

    @Test
    fun `onSignBtnClick , empty email, return DataError ENTER_EMAIL_ID `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("", "pas")
        viewModelUnderTest.showErrorDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showErrorDialog.value?.errorDescription

        assertEquals(ENTER_EMAIL_ID, loginfail)


    }


    @Test
    fun `onSignBtnClick , empty pass, return DataError ENTER_PASSWORD `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("emal", "")
        viewModelUnderTest.showErrorDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showErrorDialog.value?.errorDescription

        assertEquals(ENTER_PASSWORD, loginfail)


    }


    @Test
    fun `onSignBtnClick,filled email and password,return success`() {
        //Given
        coEvery {
            repository.login("username@gmail.com", "password@123")
        } returns testDataClassGenerator.getLoginResponse()
        every {
            repository.setUserLoggedIn(userId = any(),
                userName = any(),
                email = any(), accessToken = any())
        } returns Unit

        val observer = mockk<Observer<Boolean>> { every { onChanged(any()) } just Runs }
        viewModelUnderTest.showDialogLoadingPrivate.observeForever(observer)

        //when
        viewModelUnderTest.onSignInBtnClick("username@gmail.com", "password@123")
        viewModelUnderTest.loginResponsePrivate.observeForever {}

        //then
        val loginResponse = viewModelUnderTest.loginResponsePrivate.value!!.data
        val userId = loginResponse!!.data.userId
        val token = loginResponse.data.token
        val userType = loginResponse.data.userType


        verify {
            repository.setUserLoggedIn(userId = userId,
                userName = userType,
                email = userId, accessToken = token)
        }
        assertEquals (true, observer.onChanged(true))

        val loginResult = viewModelUnderTest.loginResponsePrivate.value?.data?.status
        assertEquals(true, loginResult)


    }


}