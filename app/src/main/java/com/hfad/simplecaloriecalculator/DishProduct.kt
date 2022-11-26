package com.hfad.simplecaloriecalculator

import android.util.Log.i
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "dish_product",
    foreignKeys = [
        ForeignKey(
            entity = DishE::class,
            parentColumns = ["id"],
            childColumns = ["dish_id"]
        ),

        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["product_id"]
        )
    ]
)
data class DishProduct(
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "weight")
    val weight: Double = 0.0
)
