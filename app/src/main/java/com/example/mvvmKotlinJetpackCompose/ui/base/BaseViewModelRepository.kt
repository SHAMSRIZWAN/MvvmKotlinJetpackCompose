package com.example.mvvmKotlinJetpackCompose.ui.base

import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider


open class BaseViewModelRepository<T:BaseRepository>(repo : T )
    : BaseViewModel<T>(repo){


        fun getRepository() : T{

            return anyType
        }


}