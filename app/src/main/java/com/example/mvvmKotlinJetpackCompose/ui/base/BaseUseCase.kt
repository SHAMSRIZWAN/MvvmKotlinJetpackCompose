package com.example.mvvmKotlinJetpackCompose.ui.base

import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider

open class BaseUseCase<R:BaseRepository>( private val repository: R, private val appDispatcher: DispatcherProvider) {
// add any common business logic

    fun getRepository():R{
        return repository
    }

    fun getAppDispatcher(): DispatcherProvider {
        return appDispatcher
    }

}