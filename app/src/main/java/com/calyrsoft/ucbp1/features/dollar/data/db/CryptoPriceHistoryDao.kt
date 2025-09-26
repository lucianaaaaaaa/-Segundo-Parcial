package com.calyrsoft.ucbp1.features.dollar.data.db // Asegúrate que el package sea correcto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoPriceHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(priceEntry: CryptoPriceHistoryEntity)

    @Query("SELECT * FROM crypto_price_history ORDER BY timestamp DESC")
    suspend fun getPriceHistory(): List<CryptoPriceHistoryEntity>
}
