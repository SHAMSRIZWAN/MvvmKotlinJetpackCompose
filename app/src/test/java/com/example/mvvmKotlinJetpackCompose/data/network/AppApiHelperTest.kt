package com.example.mvvmKotlinJetpackCompose.data.network

import android.content.Context
import com.example.mvvmKotlinJetpackCompose.util.NO_INTERNET_CONNECTION
import com.example.mvvmKotlinJetpackCompose.util.NetworkUtils
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.spyk
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import retrofit2.Response

class AppApiHelperTest {

    lateinit var sut: AppApiHelper;
    val mContextMock = mockk<Context>()
    val apiHeader: ApiHeader = mockk()

    @Before
    fun setUp() {
        sut = spyk(AppApiHelper(mContextMock, apiHeader))
    }

    @Test
    fun test(){

    }

}