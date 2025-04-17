package com.lautaroserrano.cryptotracker.crypto.presentation.coin_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lautaroserrano.cryptotracker.core.domain.util.NetworkError
import com.lautaroserrano.cryptotracker.core.domain.util.onError
import com.lautaroserrano.cryptotracker.core.domain.util.onSuccess
import com.lautaroserrano.cryptotracker.crypto.data.repository.CoinRepository
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toCoin
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toCoinPriceUi
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toCoinUi
import com.lautaroserrano.cryptotracker.crypto.presentation.models.CoinUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CoinListViewModel"

@HiltViewModel
class CoinListViewModel @Inject constructor(
    val coinRepository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                coinRepository.getCoins()
                    .onSuccess { coins ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                coins = coins.map { coin -> coin.toCoinUi() }
                            )
                        }

                    }
                    .onError { error ->
                        Log.d(TAG, "Error loading coins: $error")
                        _events.send(CoinListEvent.Error(error))
                        _state.update { it.copy(isLoading = false) }
                    }
            } catch (e: Exception) {
                Log.d(TAG, "Error loading coins: ${e.message}")
                _events.send(CoinListEvent.Error(NetworkError.UNKNOWN))
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                getCoinHistory(action.coinUi)
                _state.update { it.copy(selectedCoin = action.coinUi) }
                Log.d(TAG, "Coin clicked: ${action.coinUi.name}")
            }
        }
    }

    fun getCoinHistory(coin: CoinUi) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                coinRepository.getCoinHistory(coin.toCoin().id)
                    .onSuccess { history ->
                        _state.update { it.copy(isLoading = false) }
                        val prices = history.map { it.toCoinPriceUi() }
                        Log.d(TAG, prices.toString())
                    }
                    .onError { error ->
                        _state.update { it.copy(isLoading = false) }
                        Log.d(TAG, "Error loading coins: $error")
                        _events.send(CoinListEvent.Error(error))
                    }
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }
        }
    }
}