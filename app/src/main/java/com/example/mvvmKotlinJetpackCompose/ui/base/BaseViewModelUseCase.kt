package com.example.mvvmKotlinJetpackCompose.ui.base

import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider


open class BaseViewModelUseCase<T>(private val useCase: T)
    : BaseViewModel<T>(useCase){


        fun getUseCase() : T{

            return useCase
        }


}