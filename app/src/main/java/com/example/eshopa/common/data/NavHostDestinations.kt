package com.example.eshopa.common.data

sealed class NavHostDestinations(val route: String){
    data object ShopScreen: NavHostDestinations("shop")
    data object CartScreen: NavHostDestinations("cart")
    data object CustomerSupportScreen: NavHostDestinations("support")
    data object ProductPageScreen: NavHostDestinations("page")
    data object ProductPurchaseScreen: NavHostDestinations("purchase")
}