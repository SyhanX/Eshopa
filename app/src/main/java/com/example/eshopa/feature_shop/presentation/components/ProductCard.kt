package com.example.eshopa.feature_shop.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eshopa.R
import com.example.eshopa.feature_shop.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    isInCart: Boolean,
    onButtonClick: () -> Unit,
    onCardClick: () -> Unit
) {
    val iconId: Int
    val textId: Int
    val buttonColor: Color
    if (isInCart) {
        iconId = R.drawable.ic_add_to_cart
        textId = R.string.in_cart
        buttonColor = MaterialTheme.colorScheme.primary
    } else {
        iconId = R.drawable.ic_cart
        textId = R.string.add_to_cart
        buttonColor = MaterialTheme.colorScheme.onPrimary
    }

    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clickable { onCardClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .width(160.dp)
                .wrapContentHeight()
        ) {
            ProductImage(
                product = product,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(160.dp)
            )
            Spacer(Modifier.height(8.dp))
            ProductInfo(product = product)
            Spacer(Modifier.height(8.dp))
            AddToCartButton(
                iconId = iconId,
                textId = textId,
                modifier = Modifier
                    .width(160.dp)
                    .height(36.dp)
            ) {
                onButtonClick()
            }
        }
    }
}


@Composable
fun ProductImage(product: Product, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(product.image)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_refresh),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}

@Composable
private fun ProductInfo(product: Product) {
    Text(
        text = product.title,
        fontSize = 14.sp,
        maxLines = 2,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .wrapContentWidth()
    )
    Spacer(Modifier.height(4.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(160.dp)
    ) {
        Text(
            text = "$${product.price}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .wrapContentWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = stringResource(id = R.string.rating)
            )
            Text(
                text = "${product.rating.rate}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun AddToCartButton(
    @DrawableRes iconId: Int,
    @StringRes textId: Int,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = {
            onButtonClick()
        },
        shape = RoundedCornerShape(14.dp),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = null
        )
        Text(
            text = stringResource(textId),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}