package com.lautaroserrano.cryptotracker.crypto.presentation.coin_list

import com.lautaroserrano.cryptotracker.core.domain.util.Error

typealias DomainError = Error

sealed interface CoinListEvent {
    data class Error(val error: DomainError) : CoinListEvent
}
