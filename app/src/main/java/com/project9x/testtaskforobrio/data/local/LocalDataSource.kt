package com.project9x.testtaskforobrio.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocalDataSource {

    @Query("SELECT * FROM transaction_history")
    fun getAllTransaction(): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = TransactionEntity::class)
    fun addTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM exchange_rate")
    fun getTime(): ExchangeRateEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = ExchangeRateEntity::class)
    fun addTime(exchangeRateEntity: ExchangeRateEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE, entity = ExchangeRateEntity::class)
    fun updateTime(exchangeRateEntity: ExchangeRateEntity)


}