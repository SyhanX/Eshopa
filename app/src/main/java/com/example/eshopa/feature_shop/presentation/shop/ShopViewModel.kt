package com.example.eshopa.feature_shop.presentation.shop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshopa.common.data.ShopUiState
import com.example.eshopa.common.data.DetailsUiState
import com.example.eshopa.feature_shop.data.repository.ProductRepository
import kotlinx.coroutines.launch
import java.io.IOException

class ShopViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var shopUiState: ShopUiState by mutableStateOf(ShopUiState.Loading)
        private set

    var detailsUiState: DetailsUiState by mutableStateOf(DetailsUiState.Loading)
        private set

    init {
        getAllProducts()
    }


    fun getAllProducts() {
        viewModelScope.launch {
            shopUiState = ShopUiState.Loading
            shopUiState = try {
                ShopUiState.Success(productRepository.getAllProducts())
            } catch (e: IOException) {
                ShopUiState.Error
            } catch (e: retrofit2.HttpException) {
                ShopUiState.Error
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            detailsUiState = DetailsUiState.Loading
            detailsUiState = try {
                DetailsUiState.Success(productRepository.getProductById(id))
            } catch (e: IOException) {
                DetailsUiState.Error
            } catch (e: retrofit2.HttpException) {
                DetailsUiState.Error
            }
        }
    }
}