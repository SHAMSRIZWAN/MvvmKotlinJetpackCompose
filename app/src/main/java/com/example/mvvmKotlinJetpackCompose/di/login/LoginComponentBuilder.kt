package com.example.mvvmKotlinJetpackCompose.di.login

import com.example.mvvmKotlinJetpackCompose.data.repos.LoginRepository
import dagger.BindsInstance
import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface LoginComponentBuilder {
    fun bindLoginRepo(@BindsInstance loginRepository: LoginRepository): LoginComponentBuilder
    fun build(): LoginComponent?
}