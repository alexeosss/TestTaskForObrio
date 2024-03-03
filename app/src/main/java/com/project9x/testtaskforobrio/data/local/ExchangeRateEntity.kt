package com.project9x.testtaskforobrio.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate")
data class ExchangeRateEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "currency")
    var currency: String,

    @ColumnInfo(name = "time")
    var unixTime: Long,

    @ColumnInfo(name = "rate")
    var rate: String
)