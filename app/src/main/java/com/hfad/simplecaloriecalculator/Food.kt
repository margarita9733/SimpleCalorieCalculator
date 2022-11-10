package com.hfad.simplecaloriecalculator

interface Food {

    val id: Long

    fun getProteinsPer100(): Double
    fun getFatsPer100(): Double
    fun getCarbsPer100(): Double
    fun getCaloriesPer100(): Double
}