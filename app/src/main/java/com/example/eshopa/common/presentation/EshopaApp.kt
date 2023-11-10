package com.example.eshopa.common.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eshopa.R
import com.example.eshopa.common.data.NavHostDestinations
import com.example.eshopa.common.domain.model.BottomBarDestination
import com.example.eshopa.feature_cart.presentation.cart.CartViewModel
import com.example.eshopa.feature_shop.presentation.shop.ShopViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EshopaApp() {
    val navController = rememberNavController()
    val shopViewModel: ShopViewModel = viewModel(factory = ViewModelProvider.Factory)
    val cartViewModel: CartViewModel = viewModel(factory = ViewModelProvider.Factory)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val topBarTitle = when (currentRoute) {
        "shop" -> stringResource(R.string.shop)
        "cart" -> stringResource(R.string.cart)
        "support" -> stringResource(R.string.customer_support)
        "page" -> stringResource(R.string.view_product)
        "purchase" -> stringResource(R.string.purchase_product)
        else -> stringResource(R.string.app_name)
    }

    Scaffold(
        topBar = {
            MainTopBar(
                title = topBarTitle
            )
        },
        bottomBar = {
            BottomNavBar(navController, navBackStackEntry)
        }
    ) { scaffoldPadding ->
        AppNavHost(
            navController = navController,
            shopViewModel = shopViewModel,
            paddingValues = scaffoldPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    actions: @Composable (RowScope.() -> Unit) = { },
    navigationIcon: @Composable () -> Unit = { },
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@Composable
fun BottomNavBar(
    navHostController: NavHostController,
    navBackStackEntry: NavBackStackEntry?
) {
    val bottomBarDestinations = listOf(
        BottomBarDestination(
            route = NavHostDestinations.ShopScreen.route,
            name = R.string.shop,
            unselectedIcon = R.drawable.ic_bag_outlined,
            selectedIcon = R.drawable.ic_bag_rounded
        ),
        BottomBarDestination(
            route = NavHostDestinations.CartScreen.route,
            name = R.string.cart,
            unselectedIcon = R.drawable.ic_cart_outlined,
            selectedIcon = R.drawable.ic_cart_rounded
        ),
        BottomBarDestination(
            route = NavHostDestinations.CustomerSupportScreen.route,
            name = R.string.support,
            unselectedIcon = R.drawable.ic_support_outlined,
            selectedIcon = R.drawable.ic_support_rounded
        )

    )
    NavigationBar(
        modifier = Modifier
            .height(55.dp)
    ) {
        val currentDestination = navBackStackEntry?.destination
        bottomBarDestinations.forEach { destination ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(
                            if (isSelected) destination.selectedIcon else destination.unselectedIcon
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                selected = isSelected,
                onClick = {
                    navHostController.navigate(destination.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}