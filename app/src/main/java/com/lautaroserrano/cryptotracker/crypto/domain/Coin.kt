package com.lautaroserrano.cryptotracker.crypto.domain

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val changePercent24h: Double,
    val marketCap: Double
)
