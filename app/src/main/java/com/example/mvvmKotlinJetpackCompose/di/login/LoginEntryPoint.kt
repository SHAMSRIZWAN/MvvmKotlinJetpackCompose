package com.example.mvvmKotlinJetpackCompose.di.login

import com.example.mvvmKotlinJetpackCompose.di.login.LoginComponent
import com.example.mvvmKotlinJetpackCompose.ui.login.LoginRepo
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(LoginComponent::class)
interface LoginEntryPoint {
    fun getLoginRepo():LoginRepo
}