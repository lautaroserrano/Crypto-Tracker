package com.lautaroserrano.cryptotracker.crypto.data.service

import android.content.Context
import android.util.Log
import com.lautaroserrano.cryptotracker.core.domain.util.Error
import com.lautaroserrano.cryptotracker.core.domain.util.LocalError
import com.lautaroserrano.cryptotracker.core.domain.util.Result
import com.lautaroserrano.cryptotracker.crypto.data.mappers.toCoin
import com.lautaroserrano.cryptotracker.crypto.data.mappers.toCoinPrice
import com.lautaroserrano.cryptotracker.crypto.data.networking.dto.CoinHistoryResponseDto
import com.lautaroserrano.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.lautaroserrano.cryptotracker.crypto.domain.Coin
import com.lautaroserrano.cryptotracker.crypto.domain.CoinDataSource
import com.lautaroserrano.cryptotracker.crypto.domain.CoinPrice
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject


private const val TAG = "CoinLocalDataSource"

class CoinLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, LocalError> {
        return try {
            val json = context.assets.open("Coins.json")
                .bufferedReader()
                .use { it.readText() }
            val jsonParser = Json { ignoreUnknownKeys = true }
            val response = jsonParser.decodeFromString<CoinsResponseDto>(json)
            val coins = response.data.map { it.toCoin() }
            Result.Success(coins)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            Result.Error(LocalError.UNKNOWN)
        }
    }

    override suspend fun getCoinHistory(id: String): Result<List<CoinPrice>, Error> {
        return try {
            val json = context.assets.open("CoinHistory.json")
                .bufferedReader()
                .use { it.readText() }
            val jsonParser = Json { ignoreUnknownKeys = true }
            val response = jsonParser.decodeFromString<CoinHistoryResponseDto>(json)
            val priceHistory = response.data.map { it.toCoinPrice() }
            Result.Success(priceHistory)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            Result.Error(LocalError.UNKNOWN)
        }
    }

}