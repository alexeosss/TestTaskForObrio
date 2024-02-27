package com.project9x.testtaskforobrio.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors (
    val backgroundColor : Color,
    val primaryContentColor : Color,
    val secondaryContentColor: Color
)


val darkColorPalette = Colors(
    backgroundColor = Color(0xFF181A21),
    primaryContentColor = Color(0xFFEAECEF),
    secondaryContentColor = Color(0xFFF7931A)
)

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No colors")
}
