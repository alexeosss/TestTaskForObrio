package com.project9x.testtaskforobrio.data

import com.project9x.testtaskforobrio.data.db.ExchangeRateEntity
import com.project9x.testtaskforobrio.data.db.LocalDataSource
import com.project9x.testtaskforobrio.data.db.TransactionEntity

class RepositoryImpl(val localDataSource: LocalDataSource) : Repository {
    override suspend fun getAllTransaction(): List<TransactionEntity> {
        return localDataSource.getAllTransaction()
    }

    override suspend fun addTransaction(transactionEntity: TransactionEntity) {
        localDataSource.addTransaction(transactionEntity)
    }

    override suspend fun getExchangeRate(): ExchangeRateEntity {
        return localDataSource.getTime()
    }

    override suspend fun updateExchangeRate(exchangeRateEntity: ExchangeRateEntity) {
        localDataSource.updateTime(exchangeRateEntity)
    }


}