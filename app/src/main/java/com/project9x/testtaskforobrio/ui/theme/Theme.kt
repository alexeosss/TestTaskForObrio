package com.project9x.testtaskforobrio.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun TestTaskTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorProvider provides darkColorPalette,
        LocalTypographyProvider provides typography,
        content = content,
    )

}

object AppTheme{
    val colors : Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current

    val typography : Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypographyProvider.current
}

