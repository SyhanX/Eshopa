package com.example.eshopa.feature_shop.presentation.product_details

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.eshopa.R
import com.example.eshopa.common.data.SingleProductUiState
import com.example.eshopa.common.presentation.ErrorScreen
import com.example.eshopa.common.presentation.LoadingScreen
import com.example.eshopa.feature_shop.domain.model.Product
import com.example.eshopa.feature_shop.presentation.components.ProductImage

@Composable
fun DetailsScreen(
    singleProductUiState: SingleProductUiState,
    retryAction: () -> Unit
) {
    when (singleProductUiState) {
        is SingleProductUiState.Loading -> LoadingScreen()
        is SingleProductUiState.Error -> ErrorScreen(retryAction)
        is SingleProductUiState.Success -> PageContent(singleProductUiState.product)
    }
}

@Composable
fun PageContent(product: Product) {
    var isExpanded by remember { mutableStateOf(false) }
    val maxLines: Int
    val arrowIcon: Int
    val contentDescription: Int
    if (isExpanded) {
        maxLines = 99
        arrowIcon = R.drawable.ic_arrow_up
        contentDescription = R.string.collapse
    } else {
        maxLines = 3
        arrowIcon = R.drawable.ic_arrow_down
        contentDescription = R.string.expand
    }

    LazyColumn(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        item {
            ImageAndTitle(product = product)
            Spacer(Modifier.height(12.dp))
        }
        item {
            AdditionalInfo(product = product)
            Spacer(Modifier.height(12.dp))
        }
        item {
            Description(
                product = product,
                maxLines = maxLines,
                arrowIcon = arrowIcon,
                contentDescription = contentDescription
            ) {
                isExpanded = !isExpanded
            }
        }
    }
}

@Composable
private fun ImageAndTitle(product: Product) {
    Card {
        ProductImage(
            product = product,
            modifier = Modifier
                .size(450.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = product.title,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
private fun AdditionalInfo(product: Product) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Price(product = product)
        Rating(product = product)
        Category(product = product)
    }
}

@Composable
private fun Price(product: Product) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.price) + ":",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$${product.price}",
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Category(product: Product) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.category) + ":",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = product.category,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Rating(product: Product) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.rating) + ":",
                fontWeight = FontWeight.SemiBold
            )
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = stringResource(R.string.rating)
                )
                Text(
                    text = product.rating.rate.toString(),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun Description(
    product: Product,
    maxLines: Int,
    @DrawableRes arrowIcon: Int,
    @StringRes contentDescription: Int,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.description) + ":",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = product.description,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                maxLines = maxLines,
            )
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.weight(1F))
                Icon(
                    painter = painterResource(arrowIcon),
                    contentDescription = stringResource(contentDescription),
                    modifier = Modifier
                        .size(32.dp)
                )
                Spacer(Modifier.weight(1F))
            }
        }
    }
}