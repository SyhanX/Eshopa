package com.example.eshopa.common.data

import android.content.Context
import com.example.eshopa.common.data.datasource.MainDatabase
import com.example.eshopa.feature_cart.domain.repository.CartItemRepository
import com.example.eshopa.feature_cart.data.repository.CartItemRepositoryImpl
import com.example.eshopa.feature_shop.data.datasource.ProductApi
import com.example.eshopa.feature_shop.data.repository.ProductRepository
import com.example.eshopa.feature_shop.domain.repository.ProductRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val productRepository: ProductRepository
    val cartItemRepository: CartItemRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val baseUrl = "https://fakestoreapi.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val productApi: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }

    override val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(productApi)
    }

    override val cartItemRepository: CartItemRepository by lazy {
        CartItemRepositoryImpl(MainDatabase.getDatabase(context).cartProductDao())
    }

}