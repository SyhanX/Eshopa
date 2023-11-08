package com.example.eshopa.feature_cart.data.datasource

import com.example.eshopa.feature_shop.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {
    @GET("products/{id}")
    suspend fun getSingleProduct(@Path("id") id: Int = 0): Product
}