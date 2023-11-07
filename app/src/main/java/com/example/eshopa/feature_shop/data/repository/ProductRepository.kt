package com.example.eshopa.feature_shop.data.repository

import com.example.eshopa.feature_shop.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts() : List<Product>
    suspend fun getProductById(id: Int) : Product
}