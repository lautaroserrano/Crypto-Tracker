package com.lautaroserrano.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lautaroserrano.cryptotracker.core.presentation.util.ObserveAsEvents
import com.lautaroserrano.cryptotracker.core.presentation.theme.CryptoTrackerTheme
import com.lautaroserrano.cryptotracker.core.presentation.util.errorToString
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.CoinListAction
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.lautaroserrano.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val context = LocalContext.current
                    val viewModel: CoinListViewModel = hiltViewModel()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ObserveAsEvents(
                        events = viewModel.events
                    ) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    errorToString(
                                        error = event.error,
                                        context = context
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    if (state.selectedCoin != null) {
                        CoinDetailScreen(
                            state = state,
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.background)
                                .fillMaxSize()
                                .padding(innerPadding)
                        )
                    } else {
                        CoinListScreen(
                            state = state,
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.background)
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) { coin ->
                            viewModel.onAction(
                                CoinListAction.OnCoinClick(coin)
                            )
                        }
                    }
                }
            }
        }
    }
}
