package com.project9x.testtaskforobrio.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_history")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "time")
    var unixTime: Long,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "total")
    var total: String,

    @ColumnInfo(name = "balance")
    var balance: Int
)
