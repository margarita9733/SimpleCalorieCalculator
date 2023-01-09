package com.hfad.simplecaloriecalculator.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.hfad.simplecaloriecalculator.Product

@Entity(
    tableName = "dish_product_table",
    foreignKeys = [
        ForeignKey(
            entity = DishEntity::class,
            parentColumns = ["id"],
            childColumns = ["dish_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),

        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["product_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class DishProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "dish_id", index = true)
    val dishId: Long = 0,

    @ColumnInfo(name = "product_id", index = true)
    val productId: Long = 0,

    @ColumnInfo(name = "weight")
    val weight: Double = 0.0
)
