package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import com.example.mvvmKotlinJetpackCompose.R

enum class BottomNavData(val route:String,val iconId:Int,val title:String) {
    Home("home", R.drawable.ic_baseline_home_24,"Home"),
    History("history", R.drawable.ic_baseline_history_24,"History"),
    Other("other", R.drawable.ic_baseline_settings_24,"Other")
}