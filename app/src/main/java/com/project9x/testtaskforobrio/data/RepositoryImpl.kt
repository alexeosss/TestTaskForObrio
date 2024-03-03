package com.project9x.testtaskforobrio.data

import com.project9x.testtaskforobrio.data.local.ExchangeRateEntity
import com.project9x.testtaskforobrio.data.local.LocalDataSource
import com.project9x.testtaskforobrio.data.local.TransactionEntity
import com.project9x.testtaskforobrio.data.remote.RemoteDataSource
import com.project9x.testtaskforobrio.presentation.domain.ExchangeRate
import com.project9x.testtaskforobrio.presentation.domain.Transaction

internal class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getAllPageTransaction(pageSize: Int, offset: Int): List<Transaction> {

        return localDataSource.getAllPageTransaction(pageSize, offset).map {
            Transaction(it.unixTime, it.category, it.total, it.balance)
        }
    }

    override suspend fun addTransaction(transactionEntity: Transaction) {
        localDataSource.addTransaction(transactionEntity.let {
            TransactionEntity(
                unixTime = it.unixTime,
                category = it.category,
                total = it.total,
                balance = it.balance
            )
        })
    }

    override suspend fun getExchangeRate(): ExchangeRate? {
        return localDataSource.getTime()?.let {
            ExchangeRate(it.currency, it.unixTime, it.rate)
        }
    }

    override suspend fun addExchangeRate(exchangeRate: ExchangeRate) {
        return localDataSource.addTime(exchangeRate.let {
            ExchangeRateEntity(currency = it.currency, unixTime = it.unixTime, rate = it.rate)
        })
    }

    override suspend fun updateExchangeRate(unixTime: Long, rate: String, currency: String) {
        localDataSource.updateTime(unixTime, rate, currency)
    }

    override suspend fun getExchangeRateRequest(): String {
        return remoteDataSource.getExchangeRateRequest()
    }


}