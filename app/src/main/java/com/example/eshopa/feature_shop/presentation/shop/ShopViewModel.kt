package com.example.eshopa.feature_shop.presentation.shop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshopa.common.data.AllProductsUiState
import com.example.eshopa.common.data.SingleProductUiState
import com.example.eshopa.feature_shop.data.repository.ProductRepository
import kotlinx.coroutines.launch
import java.io.IOException

class ShopViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var allProductsUiState: AllProductsUiState by mutableStateOf(AllProductsUiState.Loading)
        private set

    var singleProductUiState: SingleProductUiState by mutableStateOf(SingleProductUiState.Loading)
        private set

    init {
        getAllProducts()
    }


    fun getAllProducts() {
        viewModelScope.launch {
            allProductsUiState = AllProductsUiState.Loading
            allProductsUiState = try {
                AllProductsUiState.Success(productRepository.getAllProducts())
            } catch (e: IOException) {
                AllProductsUiState.Error
            } catch (e: retrofit2.HttpException) {
                AllProductsUiState.Error
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            singleProductUiState = SingleProductUiState.Loading
            singleProductUiState = try {
                SingleProductUiState.Success(productRepository.getProductById(id))
            } catch (e: IOException) {
                SingleProductUiState.Error
            } catch (e: retrofit2.HttpException) {
                SingleProductUiState.Error
            }
        }
    }
}