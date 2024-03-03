package com.project9x.testtaskforobrio.presentation.domain

data class ExchangeRate(
    var currency: String,
    var unixTime: Long,
    var rate: String
)
