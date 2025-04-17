package com.lautaroserrano.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lautaroserrano.cryptotracker.R
import com.lautaroserrano.cryptotracker.core.presentation.theme.CryptoTrackerTheme
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_detail.components.InfoCard
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.components.mockCoin
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toCoinUi
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toDisplayableNumber
import com.lautaroserrano.cryptotracker.crypto.presentation.models.CoinUi

@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    val coin = state.selectedCoin
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
            coin?.let {
                CoinDetailContent(
                    coin = it
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailContent(
    coin: CoinUi,
    modifier: Modifier = Modifier
) {

    val isPositive = coin.changePercent24h.value > 0
    val trendingIconRes = if (isPositive) R.drawable.trending else R.drawable.trending_down
    val absolutePriceChange = (coin.price.value * (coin.changePercent24h.value / 100)).toDisplayableNumber()

    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = coin.iconRes),
            contentDescription = coin.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = coin.name,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = coin.symbol,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            InfoCard(
                title = stringResource(R.string.market_cap_rank),
                text = "$ ${coin.marketCap.formatted}",
                icon = ImageVector.vectorResource(R.drawable.stock)
            )
            InfoCard(
                title = stringResource(R.string.price),
                text = "$ ${coin.price.formatted}",
                icon = ImageVector.vectorResource(R.drawable.dollar)
            )
            InfoCard(
                title = stringResource(R.string.change_last_24),
                text = "$ ${absolutePriceChange.formatted}",
                icon = ImageVector.vectorResource(trendingIconRes),
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CoinDetailPreview(
    modifier: Modifier = Modifier
){
    CryptoTrackerTheme {
        CoinDetailContent(
            coin = mockCoin.toCoinUi(),
            modifier = modifier
        )
    }
}