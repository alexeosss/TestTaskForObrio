package com.project9x.testtaskforobrio.presentation.screens.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project9x.testtaskforobrio.data.Repository
import com.project9x.testtaskforobrio.data.local.TransactionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _uiState: MutableStateFlow<FirstUiState> = MutableStateFlow(FirstUiState())
    val uiState: StateFlow<FirstUiState> = _uiState

    init {
        getTransactions()
        checkExchangeRate()
    }

    fun obtainEvent(event: FirstScreenEvent) {
        when (event) {
            is FirstScreenEvent.MakeDeposit -> {
                makeDeposit(event.depositValue)
            }
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
                        listOfTransactions = newListOfTransactions
                    )
                }
            }


        }
    }

    private fun getTransactions() {

        viewModelScope.launch(Dispatchers.IO) {
            val listOfTransactions = repository.getAllTransaction()

            if (listOfTransactions.isNotEmpty()) {
                val balance = listOfTransactions.first().balance

                _uiState.update {
                    it.copy(
                        balance = balance,
                        listOfTransactions = listOfTransactions
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        balance = 0,
                    )
                }
            }


        }


    }

    private fun checkExchangeRate() {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeRate = repository.getExchangeRate()

            val unixTime = System.currentTimeMillis()

            if (exchangeRate != null) {
                if (unixTime - exchangeRate.unixTime > 3600){
                    //todo request

                    //todo update
                }
            }else{
                //todo request

                //todo add
            }
        }
    }

}

data class FirstUiState(
    val balance: Int? = null,
    val listOfTransactions: List<TransactionEntity> = listOf<TransactionEntity>(),
    val exchangeRate: String = ""
)

sealed interface FirstScreenEvent {
    class MakeDeposit(val depositValue: Int) : FirstScreenEvent

}