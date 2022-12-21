package com.hfad.simplecaloriecalculator.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_dish_table",
    foreignKeys = [
        ForeignKey(
            entity = MealEntity::class,
            parentColumns = ["id"],
            childColumns = ["meal_id"]
        ),
        ForeignKey(
            entity = DishEntity::class,
            parentColumns = ["id"],
            childColumns = ["dish_id"]
        )
    ]
)
data class MealDishEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "meal_id")
    val mealId: Long = 0,

    @ColumnInfo(name = "dish_id")
    val dishId: Long = 0,

    @ColumnInfo(name = "weight")
    val weight: Double = 0.0
)