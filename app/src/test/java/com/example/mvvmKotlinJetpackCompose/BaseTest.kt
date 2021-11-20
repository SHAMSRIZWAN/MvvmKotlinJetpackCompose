package com.example.mvvmKotlinJetpackCompose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmKotlinJetpackCompose.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseViewModel
import com.example.mvvmKotlinJetpackCompose.util.coroutines.TestDataClassGenerator
import com.finance.expensetracker.util.coroutines.MainCoroutineRule
import com.finance.expensetracker.util.coroutines.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

abstract class BaseTest<Vm : BaseViewModel<Repo>,Repo :BaseRepository> {
   // Subject under test
   protected   lateinit  var viewModelUnderTest: Vm

   // Use a fake Repo to be injected into the viewModel
   lateinit var  repository: Repo

   val testDataClassGenerator: TestDataClassGenerator = TestDataClassGenerator()

   // Set the main coroutines dispatcher for unit testing.
   @ExperimentalCoroutinesApi
   @get:Rule
   open val mainCoroutineRule = MainCoroutineRule()

   // Executes each task synchronously using Architecture Components.
   @get:Rule
   val instantExecutorRule = InstantTaskExecutorRule()


   @ExperimentalCoroutinesApi
   val testAppDispatcher=TestDispatcherProvider()

   @ExperimentalCoroutinesApi
   @Before
   abstract fun setUp()
}
