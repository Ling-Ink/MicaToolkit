package com.moling.micatoolkit.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)

val IndigoA200 = Color(0xFF536DFE)
val DeepPurple100 = Color(0xFFD1C4E9)

internal val wearColorPalette: Colors = Colors(
    primary = IndigoA200,
    primaryVariant = Purple700,
    secondary = DeepPurple100,
    secondaryVariant = Teal200,
    error = Red400,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black
)