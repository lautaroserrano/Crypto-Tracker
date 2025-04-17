package com.lautaroserrano.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>
)

@Serializable
data class CoinDto(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val marketCapUsd: String
)
