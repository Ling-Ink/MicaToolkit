package com.moling.micatoolkit.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val IndigoA200 = Color(0xFF536DFE)
val DeepPurple100 = Color(0xFFD1C4E9)
val Gray800 = Color(0xFF424242)
val Gray400 = Color(0xFFBDBDBD)
val Red700 = Color(0xFFD32F2F)

internal val wearColorPalette: Colors = Colors(
    primary = IndigoA200,
    secondary = DeepPurple100,
    surface = Gray800,
    onSurface = Gray400,

    primaryVariant = Purple700,
    secondaryVariant = Teal200,
    error = Red700,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black,
)