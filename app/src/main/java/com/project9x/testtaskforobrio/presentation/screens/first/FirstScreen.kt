package com.project9x.testtaskforobrio.presentation.screens.first

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project9x.testtaskforobrio.R
import com.project9x.testtaskforobrio.presentation.components.ButtonWithText
import com.project9x.testtaskforobrio.presentation.components.ExchangeRateCard
import com.project9x.testtaskforobrio.presentation.components.PopUpDeposit
import com.project9x.testtaskforobrio.presentation.components.TransactionHistoryComponent
import com.project9x.testtaskforobrio.presentation.navigation.NavigationTree
import com.project9x.testtaskforobrio.presentation.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FirstScreen(navController: NavHostController, vm: FirstViewModel = hiltViewModel()) {

    val uiState by vm.uiState.collectAsState()

    var isPopUpDepositOpen by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit, block = {
        vm.obtainEvent(FirstScreenEvent.UpdateScreenStates)
        vm.obtainEvent(FirstScreenEvent.CheckExchangeRate)
    })

    if (!scrollState.canScrollForward && uiState.isListNotFinished) {
        LaunchedEffect(key1 = uiState.page, block = {
            vm.obtainEvent(FirstScreenEvent.GetTransactionAndBalance)
        })
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
                    ExchangeRateCard(firstText = "1btc", secondText = uiState.exchangeRate)
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
                        if (uiState.balance == null) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 1.dp,
                                color = AppTheme.colors.primaryContentColor
                            )
                        } else {
                            Text(
                                text = uiState.balance.toString(),
                                style = AppTheme.typography.h1,
                                color = AppTheme.colors.primaryContentColor,
                                textAlign = TextAlign.Center
                            )
                        }
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
                    navController.navigate(NavigationTree.SecondScreen.name)
                }


            }
            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(), thickness = 1.dp, color = AppTheme.colors.secondaryContentColor
            )
            LazyColumn(
                Modifier.weight(5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = scrollState,
            ) {
                items(uiState.listOfTransactions) {
                    if (it.unixTime == 0L) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 40.dp)
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = it.category,
                                style = AppTheme.typography.h3,
                                color = AppTheme.colors.secondaryContentColor,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        TransactionHistoryComponent(
                            time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                                Date(it.unixTime)
                            ),
                            topic = it.category,
                            amount = it.total
                        )
                    }

                }
                if (uiState.isLoading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 1.dp,
                            color = AppTheme.colors.primaryContentColor
                        )
                    }
                }
            }
        }

        if (isPopUpDepositOpen) {
            Box(
                Modifier
                    .fillMaxSize()
                    .clickable {
                        isPopUpDepositOpen = false
                    }, contentAlignment = Alignment.Center
            ) {
                PopUpDeposit(crossClick = { isPopUpDepositOpen = false }) {
                    isPopUpDepositOpen = false
                    vm.obtainEvent(FirstScreenEvent.MakeDeposit(depositValue = it.toInt()))
                }
            }
        }

    }


}