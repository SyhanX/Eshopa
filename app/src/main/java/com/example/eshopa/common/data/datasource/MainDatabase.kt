package com.example.eshopa.common.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eshopa.feature_cart.data.datasource.CartItemDao
import com.example.eshopa.feature_cart.domain.model.CartItem

@Database(
    entities = [CartItem::class],
    version = 1,
    exportSchema = false,
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun cartProductDao(): CartItemDao

    companion object {

        @Volatile
        private var Instance: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MainDatabase::class.java,
                    "product_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}