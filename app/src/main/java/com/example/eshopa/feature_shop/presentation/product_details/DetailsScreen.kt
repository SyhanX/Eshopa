package com.example.eshopa.feature_shop.presentation.product_details

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eshopa.R
import com.example.eshopa.common.presentation.ErrorScreen
import com.example.eshopa.common.presentation.LoadingScreen
import com.example.eshopa.feature_shop.domain.model.Product
import com.example.eshopa.feature_shop.domain.util.DetailsUiState
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    detailsUiState: DetailsUiState,
    retryAction: () -> Unit
) {
    when (detailsUiState) {
        is DetailsUiState.Loading -> LoadingScreen()
        is DetailsUiState.Error -> ErrorScreen(retryAction)
        is DetailsUiState.Success -> ProductInfo(detailsUiState.product)
    }
}

@Composable
fun ProductInfo(product: Product) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .verticalScroll(scrollState)
    ) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .wrapContentHeight()
        ) {
            ProductImageAndTitle(product = product)
        }
        Spacer(Modifier.height(12.dp))
        Card(
            modifier = Modifier
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.product_info),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Price(product = product)
                Rating(product = product)
                Category(product = product)
                Description(
                    product = product,
                    isDescriptionExpanded = isDescriptionExpanded
                ) {
                    isDescriptionExpanded = !isDescriptionExpanded
                    coroutineScope.launch {
                        scrollState.animateScrollTo(Int.MAX_VALUE)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductImageAndTitle(product: Product) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(product.thumbnail) //TODO: change this to normal images
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_refresh),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(400.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = product.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
private fun Price(product: Product) {
    Row {
        Text(
            text = stringResource(R.string.price) + ": ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Text(
            text = "$" + "${product.price}",
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun Rating(product: Product) {
    Row {
        Text(
            text = stringResource(R.string.rating) + ": ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = stringResource(R.string.rating)
            )
            Text(
                text = product.rating.toString(),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Category(product: Product) {
    Row {
        Text(
            text = stringResource(R.string.category) + ": ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,

            )
        Text(
            text = product.category,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun Description(
    product: Product,
    isDescriptionExpanded: Boolean,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.description),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Text(
            text = product.description,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else 2,
        )
        Row(
            modifier = Modifier
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(
                    if (isDescriptionExpanded) R.string.collapse else R.string.expand
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Icon(
                painter = painterResource(
                    if (isDescriptionExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                ),
                contentDescription = stringResource(
                    if (isDescriptionExpanded) R.string.collapse else R.string.expand
                ),
                modifier = Modifier
                    .size(32.dp)
            )
        }
    }
}