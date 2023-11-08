package com.example.eshopa.feature_cart.domain.repository

import com.example.eshopa.feature_cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartItemRepository {
    fun getAllItems(): Flow<List<CartItem>>
    suspend fun insertItem(product: CartItem)
    suspend fun updateItem(product: CartItem)
    suspend fun deleteItem(product: CartItem)
}