package com.lautaroserrano.cryptotracker.crypto.presentation.mappers

import com.lautaroserrano.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.lautaroserrano.cryptotracker.crypto.domain.Coin
import com.lautaroserrano.cryptotracker.crypto.domain.CoinPrice
import com.lautaroserrano.cryptotracker.crypto.presentation.models.CoinPriceUi
import com.lautaroserrano.cryptotracker.crypto.presentation.models.CoinUi
import com.lautaroserrano.cryptotracker.crypto.presentation.models.DisplayableDateTime
import com.lautaroserrano.cryptotracker.crypto.presentation.models.DisplayableNumber
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale


fun CoinPrice.toCoinPriceUi(): CoinPriceUi {
    return CoinPriceUi(
        price = price.toDisplayableNumber(),
        dateTime = dateTime.toDisplayableDateTime()
    )
}

fun CoinUi.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        price = price.value,
        changePercent24h = changePercent24h.value,
        marketCap = marketCap.value,
    )
}

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        price = price.toDisplayableNumber(),
        changePercent24h = changePercent24h.toDisplayableNumber(),
        marketCap = marketCap.toDisplayableNumber(),
        iconRes = symbol.let { getDrawableIdForCoin(it) }
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun LocalDateTime.toDisplayableDateTime(): DisplayableDateTime {
    val sourceZone: ZoneId = ZoneOffset.UTC
    val targetZone: ZoneId = ZoneId.systemDefault()
    val zonedDateTime = this.atZone(sourceZone).withZoneSameInstant(targetZone)
    val formatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.MEDIUM)
        .withLocale(Locale.getDefault())
    return DisplayableDateTime(
        value = this,
        formatted = zonedDateTime.format(formatter)
    )
}
