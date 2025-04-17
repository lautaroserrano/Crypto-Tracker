package com.lautaroserrano.cryptotracker.crypto.data.service

import com.lautaroserrano.cryptotracker.BuildConfig
import com.lautaroserrano.cryptotracker.core.data.networking.safeCall
import com.lautaroserrano.cryptotracker.core.domain.util.Error
import com.lautaroserrano.cryptotracker.core.domain.util.NetworkError
import com.lautaroserrano.cryptotracker.core.domain.util.Result
import com.lautaroserrano.cryptotracker.core.domain.util.map
import com.lautaroserrano.cryptotracker.crypto.data.mappers.toCoin
import com.lautaroserrano.cryptotracker.crypto.data.mappers.toCoinPrice
import com.lautaroserrano.cryptotracker.crypto.data.networking.CoinApiV3
import com.lautaroserrano.cryptotracker.crypto.domain.Coin
import com.lautaroserrano.cryptotracker.crypto.domain.CoinDataSource
import com.lautaroserrano.cryptotracker.crypto.domain.CoinPrice
import javax.inject.Inject

class CoinRemoteDataSource @Inject constructor(
    private val api: CoinApiV3
) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall {
            api.getAssets(authorization = "Bearer ${BuildConfig.COIN_API_KEY}")
        }.map {
            it.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(id: String): Result<List<CoinPrice>, Error> {
        return safeCall {
            api.getAssetHistory(
                authorization = "Bearer ${BuildConfig.COIN_API_KEY}",
                id = id,
                interval = "h6"
            )
        }.map {
            it.data.map { it.toCoinPrice() }
        }
    }

}