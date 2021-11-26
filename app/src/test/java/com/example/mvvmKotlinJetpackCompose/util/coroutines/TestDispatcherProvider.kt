package com.finance.expensetracker.util.coroutines

import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {

    val testDispatcher = TestCoroutineDispatcher()
    val testScope = TestCoroutineScope(testDispatcher)

    override fun computation(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun io(): CoroutineDispatcher {

        return testDispatcher
    }


}