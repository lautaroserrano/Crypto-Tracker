package com.lautaroserrano.cryptotracker.crypto.data.mappers

import com.lautaroserrano.cryptotracker.crypto.data.networking.dto.CoinDto
import com.lautaroserrano.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.lautaroserrano.cryptotracker.crypto.domain.Coin
import com.lautaroserrano.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank.toInt(),
        price = priceUsd.toDouble(),
        changePercent24h = changePercent24Hr.toDouble(),
        marketCap = marketCapUsd.toDouble()
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    val instant = Instant.ofEpochMilli(time)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))
    return CoinPrice(
        dateTime = localDateTime,
        price = priceUsd.toDouble()
    )
}