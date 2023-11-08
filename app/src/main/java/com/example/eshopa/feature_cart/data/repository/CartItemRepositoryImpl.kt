package com.example.eshopa.feature_cart.data.repository

import com.example.eshopa.feature_cart.data.datasource.CartItemDao
import com.example.eshopa.feature_cart.domain.repository.CartItemRepository
import com.example.eshopa.feature_cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

class CartItemRepositoryImpl(private val dao: CartItemDao) : CartItemRepository {
    override fun getAllItems(): Flow<List<CartItem>> = dao.getAllItems()
    override suspend fun insertItem(product: CartItem) = dao.insert(product)
    override suspend fun updateItem(product: CartItem) = dao.update(product)
    override suspend fun deleteItem(product: CartItem) = dao.delete(product)
}