package com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lautaroserrano.cryptotracker.core.presentation.theme.CryptoTrackerTheme
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toCoinUi
import com.lautaroserrano.cryptotracker.crypto.domain.Coin
import com.lautaroserrano.cryptotracker.crypto.presentation.models.CoinUi

@Composable
fun CoinListItem(
    coin: CoinUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = coin.symbol,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = coin.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Column(
                modifier = Modifier.width(100.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "$ ${coin.price.formatted}",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall
                )
                ChangePercent(
                    percent = coin.changePercent24h
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListItemPreview() {
    CryptoTrackerTheme {
        CoinListItem(
            coin = mockCoin.toCoinUi(),
        )
    }
}

val mockCoin = Coin(
    id = "bitcoin",
    rank = 1,
    symbol = "BTC",
    name = "Bitcoin",
    price = 84233.3383453709689592,
    changePercent24h = -2.5709185259053883,
    marketCap = 541466167.8720215764870328
)