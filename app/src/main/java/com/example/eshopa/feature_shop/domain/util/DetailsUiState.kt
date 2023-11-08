package com.example.eshopa.feature_shop.domain.util

import com.example.eshopa.feature_shop.domain.model.Product

sealed interface DetailsUiState {
    data class Success(val product: Product) : DetailsUiState
    data object Error : DetailsUiState
    data object Loading : DetailsUiState
}