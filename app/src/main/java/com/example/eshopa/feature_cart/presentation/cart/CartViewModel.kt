package com.example.eshopa.feature_cart.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshopa.feature_cart.domain.model.CartItem
import com.example.eshopa.feature_cart.domain.repository.CartItemRepository
import com.example.eshopa.feature_cart.domain.util.CartItemListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartItemRepository,
) : ViewModel() {

    private val _state = mutableStateOf(CartItemListState())
    val state: State<CartItemListState> = _state

    private var getAllNotesJob: Job? = null

    init {
        getAllItems()
    }

    fun addProductToCart(product: CartItem) {
        viewModelScope.launch {
            repository.insertItem(product)
        }
    }

    fun removeProductFromCart(product: CartItem) {
        viewModelScope.launch {
            repository.deleteItem(product)
        }
    }

    private fun getAllItems() {
        getAllNotesJob?.cancel()
        getAllNotesJob = repository.getAllItems()
            .onEach { items ->
                _state.value = state.value.copy(
                    itemList = items
                )
            }.launchIn(viewModelScope)
    }

}