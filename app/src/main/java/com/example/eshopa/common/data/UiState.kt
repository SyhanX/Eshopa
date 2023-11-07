package com.example.eshopa.common.data

import com.example.eshopa.feature_shop.domain.model.Product

sealed interface ShopUiState {
    data class Success(val products: List<Product>) : ShopUiState
    data object Error : ShopUiState
    data object Loading : ShopUiState
}

sealed interface DetailsUiState {
    data class Success(val product: Product) : DetailsUiState
    data object Error : DetailsUiState
    data object Loading : DetailsUiState
}