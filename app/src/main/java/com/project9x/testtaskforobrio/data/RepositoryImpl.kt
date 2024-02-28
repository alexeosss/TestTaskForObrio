package com.project9x.testtaskforobrio.data

import com.project9x.testtaskforobrio.data.local.ExchangeRateEntity
import com.project9x.testtaskforobrio.data.local.LocalDataSource
import com.project9x.testtaskforobrio.data.local.TransactionEntity
import com.project9x.testtaskforobrio.data.remote.RemoteDataSource
import org.json.JSONObject

class RepositoryImpl(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getAllTransaction(): List<TransactionEntity> {
        return localDataSource.getAllTransaction().reversed()
    }

    override suspend fun addTransaction(transactionEntity: TransactionEntity) {
        localDataSource.addTransaction(transactionEntity)
    }

    override suspend fun getExchangeRate(): ExchangeRateEntity? {
        return localDataSource.getTime()
    }

    override suspend fun addExchangeRate(exchangeRateEntity: ExchangeRateEntity){
        return localDataSource.addTime(exchangeRateEntity)
    }

    override suspend fun updateExchangeRate(exchangeRateEntity: ExchangeRateEntity) {
        localDataSource.updateTime(exchangeRateEntity)
    }

    override suspend fun getExchangeRateRequest(): JSONObject {
        return remoteDataSource.getExchangeRateRequest()
    }


}