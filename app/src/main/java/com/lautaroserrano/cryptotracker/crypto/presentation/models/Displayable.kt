package com.lautaroserrano.cryptotracker.crypto.presentation.models

import java.time.LocalDateTime

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

data class DisplayableDateTime(
    val value: LocalDateTime,
    val formatted: String
)