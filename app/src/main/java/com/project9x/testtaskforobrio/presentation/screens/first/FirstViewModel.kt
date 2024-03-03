package com.project9x.testtaskforobrio.presentation.screens.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project9x.testtaskforobrio.data.Repository
import com.project9x.testtaskforobrio.data.local.ExchangeRateEntity
import com.project9x.testtaskforobrio.data.local.TransactionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _uiState: MutableStateFlow<FirstUiState> = MutableStateFlow(FirstUiState())
    val uiState: StateFlow<FirstUiState> = _uiState

    fun obtainEvent(event: FirstScreenEvent) {
        when (event) {
            is FirstScreenEvent.MakeDeposit -> {
                makeDeposit(event.depositValue)
            }

            is FirstScreenEvent.GetTransactionAndBalance -> {
                getTransactions()
            }

            is FirstScreenEvent.UpdateScreenStates -> {
                updateScreenStates()
            }

            is FirstScreenEvent.CheckExchangeRate -> {
                checkExchangeRate()
            }
        }
    }

    private fun updateScreenStates() {
        _uiState.update {
            it.copy(
                balance = null,
                listOfTransactions = listOf<TransactionEntity>(),
                page = 0,
                depositCounter = 0,
                isLoading = false,
                isListNotFinished = true
            )
        }
    }

    private fun makeDeposit(depositValue: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val unixTime = System.currentTimeMillis()

            val newBalance = uiState.value.balance?.plus(depositValue)

            newBalance?.let {
                TransactionEntity(
                    unixTime = unixTime,
                    category = "deposit",
                    total = "+$depositValue btc",
                    balance = it
                )
            }?.let {
                repository.addTransaction(
                    it
                )
                _uiState.update { it2 ->

                    val newListOfTransactions =
                        uiState.value.listOfTransactions.toMutableList().also { it3 ->
                            it3.add(
                                0, TransactionEntity(
                                    unixTime = unixTime,
                                    category = "deposit",
                                    total = "+$depositValue btc",
                                    balance = newBalance
                                )
                            )
                        }

                    it2.copy(
                        balance = newBalance,
                        listOfTransactions = groupTransactionsByDate(newListOfTransactions),
                        depositCounter = uiState.value.depositCounter + 1
                    )
                }
            }


        }
    }

    private fun getTransactions() {

        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            val pageSize = 20
            val listOfTransactionsFromDb = repository.getAllPageTransaction(
                pageSize,
                pageSize * uiState.value.page + uiState.value.depositCounter
            )

            if (listOfTransactionsFromDb.isEmpty()) {
                _uiState.update {
                    it.copy(
                        isListNotFinished = false,
                        isLoading = false,
                        balance = if (uiState.value.balance == null) 0 else uiState.value.balance
                    )
                }
            } else {
                val listOfTransactions = uiState.value.listOfTransactions + listOfTransactionsFromDb

                if (listOfTransactions.isNotEmpty()) {
                    val balance = if (listOfTransactions[0].unixTime == 0L) {
                        listOfTransactions[1].balance
                    } else {
                        listOfTransactions[0].balance
                    }

                    _uiState.update {
                        it.copy(
                            balance = balance,
                            listOfTransactions = groupTransactionsByDate(listOfTransactions),
                            isLoading = false,
                            page = uiState.value.page + 1
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            balance = 0,
                            isLoading = false,
                            page = uiState.value.page + 1
                        )
                    }
                }
            }
        }
    }

    private fun checkExchangeRate() {
        viewModelScope.launch(Dispatchers.IO) {

            val exchangeRate = repository.getExchangeRate()
            val unixTime = System.currentTimeMillis()

            if (exchangeRate != null) {
                if (unixTime - exchangeRate.unixTime > 3600000) {
                    val rate = "${parseExchangeRateRequest(repository.getExchangeRateRequest())} $"

                    _uiState.update {
                        it.copy(
                            exchangeRate = rate
                        )
                    }

                    repository.updateExchangeRate(
                        unixTime = unixTime,
                        rate = rate,
                        currency = "bitcoin"
                    )
                } else {
                    _uiState.update {
                        it.copy(
                            exchangeRate = exchangeRate.rate
                        )
                    }
                }
            } else {
                val rate = "${parseExchangeRateRequest(repository.getExchangeRateRequest())} $"

                _uiState.update {
                    it.copy(
                        exchangeRate = rate
                    )
                }

                repository.addExchangeRate(
                    ExchangeRateEntity(
                        unixTime = unixTime,
                        rate = rate,
                        currency = "bitcoin"
                    )
                )
            }
        }
    }

    private fun parseExchangeRateRequest(exchangeRateRequest: String): String {
        return Gson().fromJson(
            exchangeRateRequest,
            ParsingClass::class.java
        ).bpi.USD.rate.split(".").first()
    }

    private fun groupTransactionsByDate(listTransactions: List<TransactionEntity>): List<TransactionEntity> {
        val newFormatTransactionList = mutableListOf<TransactionEntity>()

        listTransactions.forEach {
            if (newFormatTransactionList.contains(
                    TransactionEntity(
                        unixTime = 0L,
                        category = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                            Date(it.unixTime)
                        ),
                        total = "0",
                        balance = 0
                    )
                )
            ) {
                newFormatTransactionList.add(it)
            } else {
                if (it.unixTime != 0L) {
                    newFormatTransactionList.add(
                        TransactionEntity(
                            unixTime = 0L,
                            category = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                                Date(it.unixTime)
                            ),
                            total = "0",
                            balance = 0
                        )
                    )
                    newFormatTransactionList.add(it)
                }
            }
        }

        return newFormatTransactionList
    }

}

data class FirstUiState(
    val balance: Int? = null,
    val listOfTransactions: List<TransactionEntity> = listOf<TransactionEntity>(),
    val exchangeRate: String = "",
    val page: Int = 0,
    val depositCounter: Int = 0,
    val isLoading: Boolean = false,
    val isListNotFinished: Boolean = true
)

sealed interface FirstScreenEvent {
    data object GetTransactionAndBalance : FirstScreenEvent
    data object UpdateScreenStates : FirstScreenEvent
    data object CheckExchangeRate : FirstScreenEvent
    class MakeDeposit(val depositValue: Int) : FirstScreenEvent
}

data class ParsingClass(
    val bpi: Bpi
)

data class Bpi(
    val USD: USD
)

data class USD(
    val rate: String
)