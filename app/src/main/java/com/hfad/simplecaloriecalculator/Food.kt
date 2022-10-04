package com.hfad.simplecaloriecalculator

interface Food {
    fun getProteinsPer100(proteinsPerGram: Double): Double
    fun getFatsPer100(fatsPerGram: Double): Double
    fun getCarbsPer100(carbsPerGram: Double): Double
    fun getCaloriesPer100(caloriesPerGram: Double): Double
}