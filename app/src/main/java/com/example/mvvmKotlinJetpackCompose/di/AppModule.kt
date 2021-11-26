package com.example.mvvmKotlinJetpackCompose.di

import com.example.mvvmKotlinJetpackCompose.data.network.ApiHeader
import com.example.mvvmKotlinJetpackCompose.data.network.ApiHelper
import com.example.mvvmKotlinJetpackCompose.data.network.AppApiHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.AppPreferencesHelper
import com.example.mvvmKotlinJetpackCompose.data.prefs.PreferencesHelper
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseRepository
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.DashboardRepo
import com.example.mvvmKotlinJetpackCompose.ui.login.RegistrationRepo
import com.example.mvvmKotlinJetpackCompose.util.PREF_NAME
import com.example.mvvmKotlinJetpackCompose.util.coroutines.AppDispatcherProvider
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class AppModule {


    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: AppApiHelper): ApiHelper {
        return apiHelper
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return PREF_NAME
    }

    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return ""
    }


    @Provides
    @Singleton
    fun provideProtectedApiHeader(@ApiInfo apiKey:String,preferencesHelper : PreferencesHelper)
    : ApiHeader.ProtectedApiHeader{
        return ApiHeader.ProtectedApiHeader(
            preferencesHelper.getAccessToken() ?:apiKey,
            preferencesHelper.getCurrentUserId(),
            preferencesHelper.getAccessToken())
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
    return appPreferencesHelper
    }


    @Provides
    @Singleton
    fun provideDispatcher(dispatcherProvider: AppDispatcherProvider): DispatcherProvider {
    return dispatcherProvider
    }


    @Provides
    @Singleton
//    @RegistrationScope
    fun provideRegistrationRepo(registrationRepo: RegistrationRepo): BaseRepository {
    return registrationRepo
    }

    @Provides
    @Singleton
    fun provideDashboardRepo(dashboardRepo: DashboardRepo): BaseRepository {
        return dashboardRepo
    }




}