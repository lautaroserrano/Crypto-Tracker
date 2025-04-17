package com.lautaroserrano.cryptotracker.crypto.domain

import com.lautaroserrano.cryptotracker.core.domain.util.Error
import com.lautaroserrano.cryptotracker.core.domain.util.Result

interface CoinDataSource {

    suspend fun getCoins(): Result<List<Coin>, Error>
    suspend fun getCoinHistory(id: String): Result<List<CoinPrice>, Error>

}