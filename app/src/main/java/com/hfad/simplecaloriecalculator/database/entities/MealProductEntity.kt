package com.hfad.simplecaloriecalculator.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.hfad.simplecaloriecalculator.Product

@Entity(
    tableName = "meal_product_table",
    foreignKeys = [
        ForeignKey(
            entity = MealEntity::class,
            parentColumns = ["id"],
            childColumns = ["meal_id"]
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["product_id"]
        )
    ]
)
data class MealProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "meal_id")
    val mealId: Long = 0,

    @ColumnInfo(name = "product_id")
    val productId: Long = 0,

    @ColumnInfo(name = "weight")
    val weight: Double = 0.0
)
