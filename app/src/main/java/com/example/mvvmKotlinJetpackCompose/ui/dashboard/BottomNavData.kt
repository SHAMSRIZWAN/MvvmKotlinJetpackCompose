package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import com.example.mvvmKotlinJetpackCompose.R

const val home="Home"
const val history="History"
const val other="Other"

enum class BottomNavData(val route:String,val iconId:Int,val title:String) {
    Home("home", R.drawable.ic_baseline_home_24,home),
    History("history", R.drawable.ic_baseline_history_24,history),
    Other("other", R.drawable.ic_baseline_settings_24,other)
}