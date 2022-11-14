package com.hfad.simplecaloriecalculator.mealscreens

import com.hfad.simplecaloriecalculator.Food

// объекты этого класса  используются для передачи объектов Product/Dish в адаптер
data class FoodToDisplay(var foodElement: Food, var id: Long, var portionEntered: Double) {

    fun getProteinsPerPortion() = foodElement.getProteinsPer100() / 100 * portionEntered
    fun getFatsPerPortion() = foodElement.getFatsPer100() / 100 * portionEntered
    fun getCarbsPerPortion() = foodElement.getCarbsPer100() / 100 * portionEntered
    fun getCaloriesPerPortion() = foodElement.getCaloriesPer100() / 100 * portionEntered

}
