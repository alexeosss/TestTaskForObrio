package com.project9x.testtaskforobrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.project9x.testtaskforobrio.ui.theme.AppTheme
import com.project9x.testtaskforobrio.ui.theme.TestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.backgroundColor
                ) {

                }
            }
        }
    }
}


