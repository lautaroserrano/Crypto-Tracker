package com.lautaroserrano.cryptotracker.core.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Light theme colors
val md_theme_light_success = Color(0xFF00C853)
val md_theme_light_onSuccess = Color(0xFFFFFFFF)
val md_theme_light_successContainer = Color(0xFFB9F6CA)
val md_theme_light_onSuccessContainer = Color(0xFF00391A)

// Dark theme colors
val md_theme_dark_success = Color(0xFF64DD17)
val md_theme_dark_onSuccess = Color(0xFF000000)
val md_theme_dark_successContainer = Color(0xFF1B5E20)
val md_theme_dark_onSuccessContainer = Color(0xFFFFFFFF)

@Immutable
data class ExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color
)
