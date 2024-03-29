package com.project9x.testtaskforobrio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class, ExchangeRateEntity::class], version = 1)
internal abstract class LocalDB: RoomDatabase() {
    abstract fun dao(): LocalDataSource
}