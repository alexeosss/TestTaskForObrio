package com.project9x.testtaskforobrio.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project9x.testtaskforobrio.presentation.components.ButtonWithText
import com.project9x.testtaskforobrio.presentation.components.ExchangeRateCard
import com.project9x.testtaskforobrio.presentation.components.PopUpDeposit
import com.project9x.testtaskforobrio.presentation.components.TransactionHistoryComponent
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme
import com.project9x.testtaskforobrio.presentation.ui.theme.TestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskTheme {

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.backgroundColor), contentAlignment = Alignment.Center){


                }
            }
        }
    }
}


