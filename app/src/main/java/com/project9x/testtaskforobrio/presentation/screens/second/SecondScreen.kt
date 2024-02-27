package com.project9x.testtaskforobrio.presentation.screens.second

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(){

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
    }
}