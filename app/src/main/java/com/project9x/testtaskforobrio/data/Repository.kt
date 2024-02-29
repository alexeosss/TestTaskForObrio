package com.project9x.testtaskforobrio.data

import com.project9x.testtaskforobrio.data.local.ExchangeRateEntity
import com.project9x.testtaskforobrio.data.local.TransactionEntity
import org.json.JSONObject

interface Repository {

    suspend fun getAllPageTransaction(pageSize: Int, offset: Int): List<TransactionEntity>

    suspend fun addTransaction(transactionEntity: TransactionEntity)

    suspend fun getExchangeRate(): ExchangeRateEntity?

    suspend fun addExchangeRate(exchangeRateEntity: ExchangeRateEntity)

    suspend fun updateExchangeRate(unixTime: Long, rate: String, currency: String)

    suspend fun getExchangeRateRequest(): String



}