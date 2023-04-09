package com.example.mvvmKotlinJetpackCompose.di.login

import com.example.mvvmKotlinJetpackCompose.data.repos.LoginRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(LoginComponent::class)
interface LoginEntryPoint {
    fun getLoginRepo(): LoginRepository
}