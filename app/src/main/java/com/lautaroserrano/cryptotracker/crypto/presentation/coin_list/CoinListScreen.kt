package com.lautaroserrano.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lautaroserrano.cryptotracker.core.presentation.theme.CryptoTrackerTheme
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toCoinUi
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.components.mockCoin
import com.lautaroserrano.cryptotracker.crypto.presentation.models.CoinUi

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
    onCoinClick: (CoinUi) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = state.coins) {
                    CoinListItem(
                        coin = it,
                        modifier = Modifier.fillMaxWidth()
                    ) { onCoinClick(it) }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListScreenPreview(
    modifier: Modifier = Modifier
) {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(coins = (1..100).map {
                mockCoin.copy(id = it.toString()).toCoinUi()
            }),
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
        )
    }
}
