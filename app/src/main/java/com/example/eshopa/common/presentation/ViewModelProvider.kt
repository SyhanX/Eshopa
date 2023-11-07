package com.example.eshopa.common.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.eshopa.feature_cart.presentation.cart.CartViewModel
import com.example.eshopa.feature_shop.presentation.shop.ShopViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ShopViewModel(eshopaApplication().appContainer.productRepository)
        }
        initializer {
            CartViewModel(eshopaApplication().appContainer.cartItemRepository)
        }
    }
}

fun CreationExtras.eshopaApplication(): EshopaApplication {
    return (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EshopaApplication)
}