package com.project9x.testtaskforobrio.data.remote

import org.json.JSONObject
import retrofit2.http.GET

interface RemoteDataSource {
    @GET("v1/bpi/currentprice.json")
    suspend fun getExchangeRateRequest(): JSONObject
}