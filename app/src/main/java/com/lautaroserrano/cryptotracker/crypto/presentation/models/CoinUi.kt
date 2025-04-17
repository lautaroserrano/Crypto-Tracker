package com.lautaroserrano.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes

data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: DisplayableNumber,
    val changePercent24h: DisplayableNumber,
    val marketCap: DisplayableNumber,
    @DrawableRes val iconRes: Int
)
