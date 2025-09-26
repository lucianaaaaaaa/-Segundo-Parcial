package com.calyrsoft.ucbp1.features.dollar.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.calyrsoft.ucbp1.navigation.Screen
import org.koin.androidx.compose.koinViewModel


@Composable
fun DollarScreen(
    navController: NavController,
    viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(
                text = stateValue.message,
                color = MaterialTheme.colorScheme.error
            )
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                Text(
                    text = "Fecha de Actualizacion: 25/09/2025",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 8.dp)
                ) {
                    // Oficial
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Oficial:",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stateValue.data.dollarOfficial ?: "-",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    // Paralelo
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Paralelo:",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stateValue.data.dollarParallel ?: "-",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    // USDT a Bs
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "USDT a Bs:",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stateValue.data.usdtPriceBs ?: "-",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    // USDC a Bs
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "USDC a Bs:",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stateValue.data.usdcPriceBs ?: "-",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        androidx.compose.material3.Button(onClick = { navController.navigate(Screen.PopularMovies.route) }) {
            Text("Ir a Movies")
        }
        androidx.compose.material3.Button(onClick = { navController.navigate(Screen.Github.route) }) {
            Text("Ir a Github")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SimpleDollarScreen() {
    MaterialTheme {
        val previewData = com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel(
            dollarOfficial = "6.96",
            dollarParallel = "7.25",
            usdtPriceBs = "7.18",
            usdcPriceBs = "7.15"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Fecha de Actualizacion: 25/09/2025",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Oficial:", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(previewData.dollarOfficial ?: "-", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Paralelo:", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(previewData.dollarParallel ?: "-", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
                }
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("USDT a Bs:", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(previewData.usdtPriceBs ?: "-", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                }
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("USDC a Bs:", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(previewData.usdcPriceBs ?: "-", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
