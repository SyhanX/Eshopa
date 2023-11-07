package com.example.eshopa.common.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eshopa.common.presentation.util.NavHostDestinations
import com.example.eshopa.feature_cart.presentation.cart.CartScreen
import com.example.eshopa.feature_cart.presentation.cart.CartViewModel
import com.example.eshopa.feature_shop.presentation.product_details.DetailsScreen
import com.example.eshopa.feature_shop.presentation.shop.ShopScreen
import com.example.eshopa.feature_shop.presentation.shop.ShopViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    shopViewModel: ShopViewModel,
    cartViewModel: CartViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavHostDestinations.ShopScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = NavHostDestinations.ShopScreen.route) {
            ShopScreen(
                shopViewModel = shopViewModel,
                cartViewModel = cartViewModel,
                navController = navController,
                allProductsUiState = shopViewModel.allProductsUiState,
                retryAction = shopViewModel::getAllProducts,
            )
        }
        composable(route = NavHostDestinations.ProductPageScreen.route) {
            DetailsScreen(
                singleProductUiState = shopViewModel.singleProductUiState,
                retryAction = { /*TODO*/}
            )
        }
        composable(NavHostDestinations.CartScreen.route) { CartScreen() }
//        composable(NavHostDestinations.ProductPurchaseScreen.route) { PurchaseScreen() }
    }
}