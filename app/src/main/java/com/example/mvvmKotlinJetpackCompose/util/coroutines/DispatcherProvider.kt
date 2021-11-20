package com.example.mvvmKotlinJetpackCompose.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

     fun computation (): CoroutineDispatcher
     fun io ():CoroutineDispatcher
}