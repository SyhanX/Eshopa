package com.example.eshopa.feature_cart.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshopa.feature_cart.data.repository.CartItemRepository
import com.example.eshopa.feature_cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartItemRepository,
) : ViewModel() {

    val getAllProducts: Flow<List<CartItem>> = repository.getAllItems()

    fun addItem(product: CartItem) {
        viewModelScope.launch {
            repository.insertItem(product)
        }
    }

    fun removeItem(product: CartItem) {
        viewModelScope.launch {
            repository.deleteItem(product)
        }
    }

}