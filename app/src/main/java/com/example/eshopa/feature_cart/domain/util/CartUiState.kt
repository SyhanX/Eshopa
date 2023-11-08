package com.example.eshopa.feature_cart.domain.util

import com.example.eshopa.feature_shop.domain.model.Product

sealed interface CartUiState {
    data class Success(val product: Product) : CartUiState
    data object Error : CartUiState
    data object Loading : CartUiState
}