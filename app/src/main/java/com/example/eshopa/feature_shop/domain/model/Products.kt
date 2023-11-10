package com.example.eshopa.feature_shop.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Products(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
