package com.example.eshopa.common.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eshopa.common.data.NavHostDestinations
import com.example.eshopa.feature_cart.presentation.cart.CartScreen
import com.example.eshopa.feature_cart.presentation.purchase_screen.PurchaseScreen
import com.example.eshopa.feature_shop.presentation.product_details.DetailsScreen
import com.example.eshopa.feature_shop.presentation.shop.ShopScreen
import com.example.eshopa.feature_shop.presentation.shop.ShopViewModel
import com.example.eshopa.feature_support.presentation.CustomerSupportScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    shopViewModel: ShopViewModel,
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
                navController = navController,
                shopUiState = shopViewModel.shopUiState,
                retryAction = shopViewModel::getAllProducts,
            )
        }
        composable(NavHostDestinations.CartScreen.route) { CartScreen() }
        composable(NavHostDestinations.CustomerSupportScreen.route) { CustomerSupportScreen() }
        composable(route = NavHostDestinations.ProductPageScreen.route) {
            DetailsScreen(
                detailsUiState = shopViewModel.detailsUiState,
                retryAction = { /*TODO*/}
            )
        }
        composable(NavHostDestinations.ProductPurchaseScreen.route) { PurchaseScreen() }
    }
}