package com.project9x.testtaskforobrio.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme

@Composable
fun ButtonWithText(text: String, textStyle: TextStyle, onClick: () -> Unit) {

    Button(
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.backgroundColor),
        border = BorderStroke(1.dp, AppTheme.colors.primaryContentColor)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = AppTheme.colors.primaryContentColor,
            textAlign = TextAlign.Center
        )
    }

}