package com.example.mvvmKotlinJetpackCompose.ui.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.ui.BaseInstrument
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class SplashActivityTest : BaseInstrument(){

    //    @get:Rule
//    val composeRule=createAndroidComposeRule()//if you want to test only single composable

    @get : Rule
    val composeTestRule= createAndroidComposeRule(SplashActivity::class.java)

    override fun setUp() {

    }

    @Test
    fun splash_title_isDisplayed(){
//        composeRule.setContent { //use for only single composable
//            CoinTheme{
//                YourCompose {
//
//                }
//            }
//
//        }
        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(R.string.app_name)
            )
            .assertIsDisplayed()
    }


     @Test
    fun splash_circularProgress_isDisplayed(){
//        composeRule.setContent { //use for only single composable
//            CoinTheme{
//                YourCompose {
//
//                }
//            }
//
//        }
         composeTestRule.onRoot().printToLog("MY TAG")

         composeTestRule
            .onNodeWithTag(composeTestRule.activity.getString(R.string.test_tag_circular_progress))
            .assertIsDisplayed()
    }




}