package com.hfad.simplecaloriecalculator.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.hfad.simplecaloriecalculator.Product

@Entity(
    tableName = "dish_product_table",
    foreignKeys = [
        ForeignKey(
            entity = DishEntity::class,
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
data class DishProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "weight")
    val weight: Double = 0.0
)
