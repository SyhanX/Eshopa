package com.example.eshopa.feature_cart.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eshopa.feature_cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: CartItem)

    @Update
    suspend fun update(product: CartItem)

    @Delete
    suspend fun delete(product: CartItem)

    @Query("SELECT * FROM cart_item_table ORDER BY id ASC")
    fun getAllItems() : Flow<List<CartItem>>
}