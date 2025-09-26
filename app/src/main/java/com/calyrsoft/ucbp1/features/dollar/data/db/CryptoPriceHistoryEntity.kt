package com.calyrsoft.ucbp1.features.dollar.data.db // Asegúrate que el package sea correcto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "crypto_price_history")
data class CryptoPriceHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val usdtPrice: Double,
    val usdcPrice: Double,
    val timestamp: Date = Date()
)
