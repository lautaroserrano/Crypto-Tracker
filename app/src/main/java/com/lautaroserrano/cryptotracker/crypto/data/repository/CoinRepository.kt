package com.lautaroserrano.cryptotracker.crypto.data.repository

import com.lautaroserrano.cryptotracker.core.domain.util.Error
import com.lautaroserrano.cryptotracker.core.domain.util.Result
import com.lautaroserrano.cryptotracker.crypto.domain.Coin
import com.lautaroserrano.cryptotracker.crypto.domain.CoinDataSource
import com.lautaroserrano.cryptotracker.crypto.domain.CoinPrice
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val coinLocalDataSource: CoinDataSource,
    private val coinRemoteDataSource: CoinDataSource,
): CoinDataSource {

    override suspend fun getCoins(
    ): Result<List<Coin>, Error> = coinLocalDataSource.getCoins()

    override suspend fun getCoinHistory(
        id: String
    ): Result<List<CoinPrice>, Error> = coinLocalDataSource.getCoinHistory(id = id)

}