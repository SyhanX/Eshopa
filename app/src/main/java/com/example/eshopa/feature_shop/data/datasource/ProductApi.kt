package com.example.eshopa.feature_shop.data.datasource

import com.example.eshopa.feature_shop.domain.model.Product
import com.example.eshopa.feature_shop.domain.model.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products/{id}")
    suspend fun getSingleProduct(@Path("id") id: Int = 0): Product

    @GET("products")
    suspend fun getAllProducts(): Products

}