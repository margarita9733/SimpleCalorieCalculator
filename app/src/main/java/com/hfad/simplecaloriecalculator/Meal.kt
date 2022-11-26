package com.hfad.simplecaloriecalculator

import com.hfad.simplecaloriecalculator.mealscreens.FoodToDisplay

class Meal(
    var id: Long = 0,
    var month: Long = 0,
    var day: Long = 0,
    var time: Long = 0,
    var food: List<FoodToDisplay> = listOf()
) {
    val proteinsTotal get() = food.sumOf { it.getProteinsPerPortion() }
    val fatsTotal get() = food.sumOf { it.getFatsPerPortion() }
    val carbsTotal get() = food.sumOf { it.getCarbsPerPortion() }
    val caloriesTotal get() = food.sumOf { it.getCaloriesPerPortion() }
}