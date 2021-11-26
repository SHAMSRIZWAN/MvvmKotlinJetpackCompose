package com.example.mvvmKotlinJetpackCompose.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

     fun computation (): CoroutineDispatcher=Dispatchers.Default
     fun io ():CoroutineDispatcher=Dispatchers.IO

}