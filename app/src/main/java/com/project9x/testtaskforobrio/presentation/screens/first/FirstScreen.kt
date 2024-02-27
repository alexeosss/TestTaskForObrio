package com.project9x.testtaskforobrio.presentation.screens.first

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project9x.testtaskforobrio.R
import com.project9x.testtaskforobrio.presentation.components.ButtonWithText
import com.project9x.testtaskforobrio.presentation.components.ExchangeRateCard
import com.project9x.testtaskforobrio.presentation.components.PopUpDeposit
import com.project9x.testtaskforobrio.presentation.components.TransactionHistoryComponent
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme

@Composable
fun FirstScreen(navController: NavHostController) {

    var isPopUpDepositOpen by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier.weight(3f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ExchangeRateCard(firstText = "1btc", secondText = "56800$")
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "1000",
                            style = AppTheme.typography.h1,
                            color = AppTheme.colors.primaryContentColor,
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_btc),
                            null,
                            tint = AppTheme.colors.primaryContentColor
                        )
                    }

                    ButtonWithText(
                        text = stringResource(id = R.string.deposit),
                        textStyle = AppTheme.typography.h2
                    ) {
                        isPopUpDepositOpen = true
                    }
                }

                ButtonWithText(
                    text = stringResource(id = R.string.add_transaction),
                    textStyle = AppTheme.typography.h2
                ) {

                }


            }
            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(), thickness = 1.dp, color = AppTheme.colors.secondaryContentColor
            )
            LazyColumn(Modifier.weight(5f), horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Text(
                        text = "27 february 2024",
                        style = AppTheme.typography.h3,
                        color = AppTheme.colors.secondaryContentColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                items(5) {
                    TransactionHistoryComponent(
                        time = "12:30",
                        topic = "groceries",
                        amount = "+356 btc"
                    )
                }


            }

        }

        if (isPopUpDepositOpen) {
            Box(
                Modifier
                    .fillMaxSize()
                    .clickable {
                        isPopUpDepositOpen = false
                    }, contentAlignment = Alignment.Center) {
                    PopUpDeposit(crossClick = { isPopUpDepositOpen = false }) {

                    }
            }
        }

    }


}