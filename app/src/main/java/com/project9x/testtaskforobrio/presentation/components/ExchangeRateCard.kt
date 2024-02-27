package com.project9x.testtaskforobrio.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme


@Composable
fun ExchangeRateCard(firstText: String, secondText: String) {

    Card(
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgroundColor),
        border = BorderStroke(1.dp, AppTheme.colors.primaryContentColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Text(
                text = firstText,
                style = AppTheme.typography.h3,
                color = AppTheme.colors.secondaryContentColor,
                textAlign = TextAlign.Center
            )

            Divider(
                modifier = Modifier.size(1.dp, 20.dp),
                color = AppTheme.colors.secondaryContentColor
            )

            Text(
                text = secondText,
                style = AppTheme.typography.h3,
                color = AppTheme.colors.secondaryContentColor,
                textAlign = TextAlign.Center
            )

        }
    }

}