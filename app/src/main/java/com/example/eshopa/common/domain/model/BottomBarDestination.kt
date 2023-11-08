package com.example.eshopa.common.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomBarDestination(
    val route: String,
    @StringRes val name: Int,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
)
