package com.example.eshopa.feature_cart.domain.util

import com.example.eshopa.feature_cart.domain.model.CartItem

data class CartItemListState(
    val itemList: List<CartItem> = emptyList(),
    val isLinearLayout: Boolean = false
)