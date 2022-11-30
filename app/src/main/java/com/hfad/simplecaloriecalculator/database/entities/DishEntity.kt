package com.hfad.simplecaloriecalculator.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish_table")
data class DishEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "dish_name")
    var name: String = "Блюдо $id",

    @ColumnInfo(name = "default_portion")
    var defaultPortionWeight: Double = 100.0
)