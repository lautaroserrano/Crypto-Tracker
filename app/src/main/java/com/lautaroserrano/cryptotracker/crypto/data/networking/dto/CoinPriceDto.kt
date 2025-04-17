package com.lautaroserrano.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryResponseDto(
    val data: List<CoinPriceDto>
)

@Serializable
data class CoinPriceDto(
    val priceUsd: String,
    val time: Long,
    val date: String
)

