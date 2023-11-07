package com.example.eshopa.common.presentation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.eshopa.R

sealed class NavHostDestinations(
    val route: String,
    @StringRes val name: Int,
    @DrawableRes val icon: Int
){
    object ShopScreen: NavHostDestinations("shop", R.string.shop, R.drawable.ic_bag)
    /*TODO: remove this screen*/
    object WishlistScreen: NavHostDestinations("wishlist", R.string.wishlist,  R.drawable.ic_favorite)
    object CartScreen: NavHostDestinations("cart", R.string.cart, R.drawable.ic_cart)
    object ProductPageScreen: NavHostDestinations("page", R.string.product_details, R.drawable.ic_placeholder)
    object ProductPurchaseScreen: NavHostDestinations("purchase", R.string.purchase_product, R.drawable.ic_placeholder)
}