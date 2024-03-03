package com.project9x.testtaskforobrio.presentation.screens.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project9x.testtaskforobrio.data.Repository
import com.project9x.testtaskforobrio.presentation.domain.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun obtainEvent(event: SecondScreenEvent) {
        when (event) {
            is SecondScreenEvent.AddTransaction -> {
                addTransaction(event.transactionAmount, event.categoryValue)
            }
        }
    }

    private fun addTransaction(transactionAmount: String, categoryValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val unixTime = System.currentTimeMillis()
            try {
                val newBalance = repository.getAllPageTransaction(1, 0)
                    .first().balance - transactionAmount.toInt()

                repository.addTransaction(
                    Transaction(
                        unixTime = unixTime,
                        category = categoryValue,
                        total = "-$transactionAmount btc",
                        balance = newBalance
                    )
                )
            } catch (_: Exception) {
            }

        }

    }

}


sealed interface SecondScreenEvent {
    class AddTransaction(val transactionAmount: String, val categoryValue: String) :
        SecondScreenEvent

}