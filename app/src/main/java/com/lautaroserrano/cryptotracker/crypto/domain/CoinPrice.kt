package com.lautaroserrano.cryptotracker.crypto.domain

import java.time.LocalDateTime

data class CoinPrice(
    val dateTime: LocalDateTime,
    val price: Double
)
