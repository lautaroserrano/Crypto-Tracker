package com.lautaroserrano.cryptotracker.crypto.presentation.coin_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lautaroserrano.cryptotracker.R
import com.lautaroserrano.cryptotracker.core.presentation.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    title: String,
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    textStyle: TextStyle = LocalTextStyle.current
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 5.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimatedContent(
                targetState = icon,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                label = "IconAnimation"
            ) { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .size(75.dp),
                    tint = contentColor
                )
            }
            AnimatedContent(
                targetState = text,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                label = "TextAnimation"
            ) { text ->
                Text(
                    text = text,
                    style = textStyle,
                    color = contentColor
                )
            }
            AnimatedContent(
                targetState = title,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                label = "TitleAnimation"
            ) { title ->
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    color = contentColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                )
            }
        }

    }
}


@PreviewLightDark
@Composable
fun InfoCardPreview(
    modifier: Modifier = Modifier
) {
    CryptoTrackerTheme {
        InfoCard(
            title = "Price",
            text = "$ 12,000.00",
            icon = ImageVector.vectorResource(R.drawable.dollar),
            modifier = modifier
        )
    }
}