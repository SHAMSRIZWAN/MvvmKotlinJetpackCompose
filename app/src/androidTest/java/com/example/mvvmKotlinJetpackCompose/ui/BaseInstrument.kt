package com.example.mvvmKotlinJetpackCompose.ui

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mvvmKotlinJetpackCompose.ui.splash.SplashActivity
import org.junit.Before
import org.junit.Rule

@ExperimentalFoundationApi
abstract class BaseInstrument {



    @Before
    abstract fun setUp()


}