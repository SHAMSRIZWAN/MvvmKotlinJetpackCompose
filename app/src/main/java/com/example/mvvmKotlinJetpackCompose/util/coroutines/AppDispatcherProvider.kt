package com.example.mvvmKotlinJetpackCompose.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatcherProvider @Inject constructor() : DispatcherProvider