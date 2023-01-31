package com.example.mvvmKotlinJetpackCompose.util


const val PREF_NAME = "MvvmKotlin_JetpackCompose_pref"
const val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
const val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
const val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
const val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
const val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"
const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
const val NULL_INDEX = "-1"

const val NO_INTERNET_CONNECTION = "No network Connection !"
const val NETWORK_ERROR = -2
const val ENTER_PASSWORD = "Please enter Password."
const val ENTER_EMAIL_ID = "Please enter Email Id."
const val SOMETHING_WENT_WRONG ="Something went Wrong"
const val CLIENT_SIDE_ERROR ="Client side error"
const val SERVER_SIDE_ERROR ="Server side error"



enum class LoggedInMode(val type: Int) {
    LOGGED_IN_MODE_LOGGED_OUT(0),
    LOGGED_IN_MODE_GOOGLE(1),
    LOGGED_IN_MODE_FB(2),
    LOGGED_IN_MODE_SERVER(3);

}