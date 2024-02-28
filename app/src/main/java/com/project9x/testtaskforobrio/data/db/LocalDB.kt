package com.project9x.testtaskforobrio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class, ExchangeRateEntity::class], version = 1)
abstract class LocalDB: RoomDatabase() {
    abstract fun dao(): LocalDataSource
}