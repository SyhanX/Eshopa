package com.example.eshopa.feature_cart.presentation.cart

import androidx.lifecycle.ViewModel
import com.example.eshopa.feature_cart.domain.repository.CartItemRepository

class CartViewModel(
    private val repository: CartItemRepository,
) : ViewModel() {

}