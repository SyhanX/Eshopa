package com.example.eshopa.feature_cart.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item_table")
data class CartItem(
    @PrimaryKey
    val id: Int,
    val count: Int = 1
)
