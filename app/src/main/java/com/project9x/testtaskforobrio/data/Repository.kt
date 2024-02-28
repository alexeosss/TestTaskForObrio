package com.project9x.testtaskforobrio.data

import com.project9x.testtaskforobrio.data.db.ExchangeRateEntity
import com.project9x.testtaskforobrio.data.db.TransactionEntity

interface Repository {

    suspend fun getAllTransaction(): List<TransactionEntity>

    suspend fun addTransaction(transactionEntity: TransactionEntity)

    suspend fun getExchangeRate(): ExchangeRateEntity

    suspend fun updateExchangeRate(exchangeRateEntity: ExchangeRateEntity)



}