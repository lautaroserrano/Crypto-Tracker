package com.lautaroserrano.cryptotracker.di

import android.content.Context
import com.lautaroserrano.cryptotracker.BuildConfig
import com.lautaroserrano.cryptotracker.core.data.networking.HttpClientFactory
import com.lautaroserrano.cryptotracker.crypto.data.networking.CoinApiV3
import com.lautaroserrano.cryptotracker.crypto.data.repository.CoinRepository
import com.lautaroserrano.cryptotracker.crypto.data.service.CoinLocalDataSource
import com.lautaroserrano.cryptotracker.crypto.data.service.CoinRemoteDataSource
import com.lautaroserrano.cryptotracker.crypto.domain.CoinDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideCoinRepository(
        coinLocalDataSource: CoinLocalDataSource,
        coinRemoteDataSource: CoinRemoteDataSource
    ): CoinRepository {
        return CoinRepository(
            coinLocalDataSource = coinLocalDataSource,
            coinRemoteDataSource = coinRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideCoinRemoteDataSource(
        api: CoinApiV3
    ): CoinRemoteDataSource {
        return CoinRemoteDataSource(api = api)
    }

    @Provides
    @Singleton
    fun provideCoinLocalDataSource(
        @ApplicationContext context: Context
    ): CoinLocalDataSource {
        return CoinLocalDataSource(context = context)
    }

    @Provides
    @Singleton
    fun provideCoinDataSource(
        api: CoinApiV3
    ): CoinDataSource {
        return CoinRemoteDataSource(api = api)
    }

    @Provides
    @Singleton
    fun provideCoinApiV3(
        httpClient: OkHttpClient
    ): CoinApiV3 {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.COIN_API_BASE_URL}/v3/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApiV3::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(
    ): OkHttpClient {
        return HttpClientFactory.create()
    }

}