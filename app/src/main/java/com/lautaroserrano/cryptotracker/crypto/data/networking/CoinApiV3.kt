package com.lautaroserrano.cryptotracker.crypto.data.networking

import com.lautaroserrano.cryptotracker.crypto.data.networking.dto.CoinHistoryResponseDto
import com.lautaroserrano.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface CoinApiV3 {

    @GET("assets")
    suspend fun getAssets(
        @Header("Authorization") authorization: String
    ): Response<CoinsResponseDto>

    @GET("assets/{id}/history")
    suspend fun getAssetHistory(
        @Header("Authorization") authorization: String,
        @Path("id") id: String,
        @Query("interval") interval: String
    ): Response<CoinHistoryResponseDto>

}
