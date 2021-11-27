package com.example.mvvmKotlinJetpackCompose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModel
import com.example.mvvmKotlinJetpackCompose.util.coroutines.CoroutineTestRule
import com.example.mvvmKotlinJetpackCompose.util.coroutines.TestDataClassGenerator
import com.example.mvvmKotlinJetpackCompose.util.coroutines.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

abstract class BaseTest<Vm : BaseViewModel<Repo>, Repo : BaseRepository> {
    // Subject under test
    protected lateinit var viewModelUnderTest: Vm

    // Use a mock Repo to be injected into the viewModel
    protected lateinit var repository: Repo

    protected val testDataClassGenerator: TestDataClassGenerator = TestDataClassGenerator()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    open val mainCoroutineRule = CoroutineTestRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    val appDispatcher = TestDispatcherProvider()

    @ExperimentalCoroutinesApi
    @Before
    abstract fun setUp()
}
