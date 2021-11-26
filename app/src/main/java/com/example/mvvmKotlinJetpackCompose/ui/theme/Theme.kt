package com.example.mvvmKotlinJetpackCompose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black


private val LightColorPalette = lightColors(
    primary = DarkGray,
    primaryVariant = Gray,
    secondary = LightPurple,
    onPrimary = Black,
    onSecondary = White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)



@Composable
fun LiquorCoinTheme(content: @Composable() () -> Unit) {


    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}