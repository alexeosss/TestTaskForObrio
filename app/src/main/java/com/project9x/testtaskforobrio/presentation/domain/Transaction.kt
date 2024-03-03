package com.project9x.testtaskforobrio.presentation.domain


data class Transaction(
    var unixTime: Long,
    var category: String,
    var total: String,
    var balance: Int
)
