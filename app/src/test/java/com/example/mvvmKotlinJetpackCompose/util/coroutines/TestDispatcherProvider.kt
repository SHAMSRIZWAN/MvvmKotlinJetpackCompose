package com.finance.expensetracker.util.coroutines

import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {

    private val testDispatcher=TestCoroutineDispatcher();

    override fun computation(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun io(): CoroutineDispatcher {
        return testDispatcher
    }


}