package com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.lautaroserrano.cryptotracker.core.presentation.theme.CryptoTrackerTheme
import com.lautaroserrano.cryptotracker.core.presentation.theme.LocalExtendedColors
import com.lautaroserrano.cryptotracker.crypto.presentation.mappers.toDisplayableNumber
import com.lautaroserrano.cryptotracker.crypto.presentation.models.DisplayableNumber


@Composable
fun ChangePercent(
    percent: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val extendedColors = LocalExtendedColors.current
    val isPositive = percent.value >= 0
    val colorContainer = if (isPositive) extendedColors.successContainer else MaterialTheme.colorScheme.error
    val colorOnContainer = if (isPositive) extendedColors.onSuccessContainer else MaterialTheme.colorScheme.onError
    val painter = if (isPositive) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(colorContainer),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = painter,
                contentDescription = null,
                tint = colorOnContainer,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = "${percent.formatted} %",
                color = colorOnContainer,
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}

@PreviewLightDark
@Composable
fun PositiveChangePercentPreview() {
    CryptoTrackerTheme{
    ChangePercent(
        percent = 2.5709185259053883.toDisplayableNumber()
    )
    }
}

@PreviewLightDark
@Composable
fun NegativeChangePercentPreview() {
    CryptoTrackerTheme{
        ChangePercent(
            percent = (-2.5709185259053883).toDisplayableNumber()
        )
    }
}