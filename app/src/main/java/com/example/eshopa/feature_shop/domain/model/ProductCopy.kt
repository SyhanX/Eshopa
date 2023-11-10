package com.example.eshopa.feature_shop.domain.model

//save this in case of switching back to the old API
data class ProductCopy(
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)
