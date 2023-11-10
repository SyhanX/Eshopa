package com.example.eshopa.feature_shop.data.repository

import com.example.eshopa.feature_shop.domain.model.Product
import com.example.eshopa.feature_shop.domain.model.Products

interface ProductRepository {
    suspend fun getAllProducts() : Products
    suspend fun getProductById(id: Int) : Product
}