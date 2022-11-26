package com.hfad.simplecaloriecalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class MealE(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "meal_month")
    var month: Long = 0,

    @ColumnInfo(name = "meal_day")
    var day: Long = 0,

    @ColumnInfo(name = "meal_time")
    var time: Long = 0
)