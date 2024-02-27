package com.project9x.testtaskforobrio.presentation.components

import android.view.SurfaceControl.Transaction
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme.colors
import java.time.temporal.TemporalAmount

@Composable
fun TransactionHistoryComponent(time: String, topic: String, amount: String) {

    Card(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgroundColor),
        border = BorderStroke(1.dp, AppTheme.colors.secondaryContentColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().defaultMinSize(minHeight = 40.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = time,
                style = AppTheme.typography.h3,
                color = AppTheme.colors.secondaryContentColor,
                textAlign = TextAlign.Center
            )

            Text(
                text = topic,
                style = AppTheme.typography.h3,
                color = AppTheme.colors.secondaryContentColor,
                textAlign = TextAlign.Center
            )

            Text(
                text = amount,
                style = AppTheme.typography.h3,
                color = AppTheme.colors.secondaryContentColor,
                textAlign = TextAlign.Center
            )

        }
    }

}