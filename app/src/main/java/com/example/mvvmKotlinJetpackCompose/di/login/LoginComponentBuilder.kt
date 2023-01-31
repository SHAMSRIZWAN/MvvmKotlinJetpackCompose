package com.example.mvvmKotlinJetpackCompose.di.login

import com.example.mvvmKotlinJetpackCompose.ui.login.LoginRepo
import dagger.BindsInstance
import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface LoginComponentBuilder {
    fun bindLoginRepo(@BindsInstance loginRepo: LoginRepo): LoginComponentBuilder
    fun build(): LoginComponent
}