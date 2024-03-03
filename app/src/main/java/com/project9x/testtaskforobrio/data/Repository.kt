package com.project9x.testtaskforobrio.data

import com.project9x.testtaskforobrio.presentation.domain.ExchangeRate
import com.project9x.testtaskforobrio.presentation.domain.Transaction

interface Repository {

    suspend fun getAllPageTransaction(pageSize: Int, offset: Int): List<Transaction>

    suspend fun addTransaction(transactionEntity: Transaction)

    suspend fun getExchangeRate(): ExchangeRate?

    suspend fun addExchangeRate(exchangeRate: ExchangeRate)

    suspend fun updateExchangeRate(unixTime: Long, rate: String, currency: String)

    suspend fun getExchangeRateRequest(): String



}