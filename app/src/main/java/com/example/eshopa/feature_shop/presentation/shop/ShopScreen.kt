package com.example.eshopa.feature_shop.presentation.shop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eshopa.common.data.ShopUiState
import com.example.eshopa.common.presentation.ErrorScreen
import com.example.eshopa.common.presentation.LoadingScreen
import com.example.eshopa.common.presentation.util.NavHostDestinations
import com.example.eshopa.feature_cart.domain.model.CartItem
import com.example.eshopa.feature_cart.presentation.cart.CartViewModel
import com.example.eshopa.feature_shop.domain.model.Product
import com.example.eshopa.feature_shop.presentation.components.ProductCard

@Composable
fun ShopScreen(
    shopViewModel: ShopViewModel,
    cartViewModel: CartViewModel,
    navController: NavHostController,
    shopUiState: ShopUiState,
    retryAction: () -> Unit,
) {
    when (shopUiState) {
        is ShopUiState.Loading -> LoadingScreen()
        is ShopUiState.Error -> ErrorScreen(retryAction)
        is ShopUiState.Success -> ShopContent(
            shopViewModel = shopViewModel,
            cartViewModel = cartViewModel,
            navController = navController,
            shopUiState.products
        )
    }
}

@Composable
fun ShopContent(
    shopViewModel: ShopViewModel,
    cartViewModel: CartViewModel,
    navController: NavHostController,
    products: List<Product>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        items(
            items = products,
            key = { product: Product -> product.id }
        ) { gridItem ->

            val cartProduct = CartItem(gridItem.id)

            ProductCard(
                product = gridItem,
                isInCart = false,
                onButtonClick = {
                    cartViewModel.addItem(cartProduct)
                }) {
                navController.navigate(NavHostDestinations.ProductPageScreen.route)
                shopViewModel.getProductById(gridItem.id)
            }
        }
    }
}