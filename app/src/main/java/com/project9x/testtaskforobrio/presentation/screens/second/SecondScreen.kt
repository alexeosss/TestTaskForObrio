package com.project9x.testtaskforobrio.presentation.screens.second

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project9x.testtaskforobrio.R
import com.project9x.testtaskforobrio.presentation.components.ButtonWithText
import com.project9x.testtaskforobrio.presentation.navigation.NavigationTree
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SecondScreen(navController: NavHostController) {

    BackHandler {
        navController.popBackStack(route = NavigationTree.FirstScreen.name, inclusive = false)
    }

    val pattern = remember { Regex("^[1-9]\\d*\$") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isExpanded by remember {
            mutableStateOf(false)
        }

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }, modifier = Modifier.size(160.dp, 60.dp)
        ) {

            OutlinedTextField(
                label = { Text(text = "category") },
                value = "groceries",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.menuAnchor(),
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        null,
                        Modifier.rotate(if (isExpanded) 180f else 0f),
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
                    focusedTextColor = AppTheme.colors.primaryContentColor
                )
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(AppTheme.colors.backgroundColor)
            ) {
                DropdownMenuItem(
                    modifier = Modifier
                        .size(160.dp, 60.dp)
                        .border(
                            1.dp,
                            AppTheme.colors.primaryContentColor,
                            OutlinedTextFieldDefaults.shape
                        ),
                    text = { Text(text = "1234") },
                    onClick = { isExpanded = false },
                    colors = MenuDefaults.itemColors(textColor = AppTheme.colors.primaryContentColor),
                )
                DropdownMenuItem(
                    modifier = Modifier
                        .size(160.dp, 60.dp)
                        .border(
                            1.dp,
                            AppTheme.colors.primaryContentColor,
                            OutlinedTextFieldDefaults.shape
                        ),
                    text = { Text(text = "1234") },
                    onClick = { isExpanded = false },
                    colors = MenuDefaults.itemColors(textColor = AppTheme.colors.primaryContentColor),
                )
                DropdownMenuItem(
                    modifier = Modifier
                        .size(160.dp, 60.dp)
                        .border(
                            1.dp,
                            AppTheme.colors.primaryContentColor,
                            OutlinedTextFieldDefaults.shape
                        ),
                    text = { Text(text = "1234") },
                    onClick = { isExpanded = false },
                    colors = MenuDefaults.itemColors(textColor = AppTheme.colors.primaryContentColor),
                )
            }


        }

        OutlinedTextField(
            value = "",
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    //todo save number
                }
            },
            modifier = Modifier.size(150.dp, 60.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "0",
                    color = AppTheme.colors.primaryContentColor,
                    style = AppTheme.typography.h3
                )
            },
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
            text = stringResource(id = R.string.add),
            textStyle = AppTheme.typography.h1
        ) {

        }
    }
}