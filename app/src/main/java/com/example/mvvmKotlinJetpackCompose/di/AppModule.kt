package com.example.mvvmKotlinJetpackCompose.di

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.AppApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.AppPreferencesHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.data.repos.DashboardRepository
import com.example.mvvmKotlinJetpackCompose.util.PREF_NAME
import com.example.mvvmKotlinJetpackCompose.util.coroutines.AppDispatcherProvider
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object{
        @Provides
        @PreferenceInfo
        fun providePreferenceName(): String {
            return PREF_NAME
        }

        @Provides
        @EmptyString
        fun provideApiKey(): String {
            return ""
        }

    }

    @Binds
    @Singleton
    abstract fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper

    @Binds
    @Singleton
    abstract fun providePreferenceHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper


    @Binds
    @Singleton
    abstract fun provideDispatcher(dispatcherProvider: AppDispatcherProvider): DispatcherProvider

    @Binds
    @Singleton
    abstract fun provideDashboardRepo(dashboardRepository: DashboardRepository): BaseRepository

}