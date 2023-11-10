package com.example.eshopa.feature_shop.domain.util

import com.example.eshopa.feature_shop.domain.model.Products

sealed interface ShopUiState {
    data class Success(val products: Products) : ShopUiState
    data object Error : ShopUiState
    data object Loading : ShopUiState
}