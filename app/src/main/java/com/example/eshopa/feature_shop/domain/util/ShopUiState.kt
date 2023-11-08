package com.example.eshopa.feature_shop.domain.util

import com.example.eshopa.feature_shop.domain.model.Product

sealed interface ShopUiState {
    data class Success(val products: List<Product>) : ShopUiState
    data object Error : ShopUiState
    data object Loading : ShopUiState
}