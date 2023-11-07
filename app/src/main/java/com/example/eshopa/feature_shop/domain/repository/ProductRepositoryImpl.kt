package com.example.eshopa.feature_shop.domain.repository

import com.example.eshopa.feature_shop.data.datasource.ProductApi
import com.example.eshopa.feature_shop.data.repository.ProductRepository
import com.example.eshopa.feature_shop.domain.model.Product

class ProductRepositoryImpl(private val api: ProductApi): ProductRepository {
    override suspend fun getAllProducts(): List<Product> = api.getAllProducts()
    override suspend fun getProductById(id: Int): Product = api.getSingleProduct(id)
}