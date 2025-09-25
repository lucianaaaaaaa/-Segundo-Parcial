package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                Text("Oficial")
                Text(stateValue.data.dollarOfficial!!)
                Text("Paralelo")
                Text(stateValue.data.dollarParallel!!)
                Text("USDT a Bs")
                Text(stateValue.data.usdtPriceBs!!)
                Text("USDC a Bs")
                Text(stateValue.data.usdcPriceBs!!)
            }
        }
    }
}
