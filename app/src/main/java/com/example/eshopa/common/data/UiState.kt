package com.example.eshopa.common.data

import com.example.eshopa.feature_shop.domain.model.Product

sealed interface AllProductsUiState {
    data class Success(val products: List<Product>) : AllProductsUiState
    object Error : AllProductsUiState
    object Loading : AllProductsUiState
}

sealed interface SingleProductUiState {
    data class Success(val product: Product) : SingleProductUiState
    object Error : SingleProductUiState
    object Loading : SingleProductUiState
}