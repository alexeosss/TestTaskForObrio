package com.project9x.testtaskforobrio.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.project9x.testtaskforobrio.R
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PopUpDeposit(crossClick: () -> Unit, buttonClick: (String) -> Unit) {

    val pattern = remember { Regex("^[1-9]\\d*\$") }

    val keyboardController = LocalSoftwareKeyboardController.current

    var depositValue by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier.size(300.dp, 280.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgroundColor),
        border = BorderStroke(1.dp, AppTheme.colors.primaryContentColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                Icons.Filled.Close,
                null,
                tint = AppTheme.colors.primaryContentColor,
                modifier = Modifier
                    .padding(end = 16.dp, top = 16.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable { crossClick.invoke() }
            )

            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = depositValue,
                    onValueChange = {
                        if (it.isEmpty() || it.matches(pattern)) {
                            depositValue = it
                        }
                    },
                    modifier = Modifier.size(150.dp, 60.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        autoCorrect = false
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_btc),
                            null,
                            tint = AppTheme.colors.primaryContentColor
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AppTheme.colors.primaryContentColor,
                        disabledBorderColor = AppTheme.colors.primaryContentColor,
                        unfocusedBorderColor = AppTheme.colors.primaryContentColor,
                        focusedLabelColor = AppTheme.colors.primaryContentColor,
                        disabledLabelColor = AppTheme.colors.primaryContentColor,
                        unfocusedLabelColor = AppTheme.colors.primaryContentColor,
                        disabledTextColor = AppTheme.colors.primaryContentColor,
                        unfocusedTextColor = AppTheme.colors.primaryContentColor,
                        focusedTextColor = AppTheme.colors.primaryContentColor,
                    )
                )


                ButtonWithText(
                    text = stringResource(id = R.string.deposit),
                    textStyle = AppTheme.typography.h2
                ) {
                    if (depositValue.isNotEmpty() && depositValue.matches(pattern)) {
                        buttonClick.invoke(depositValue)
                    }

                }
            }
        }
    }

}