package com.hfad.simplecaloriecalculator.mealscreens

import com.hfad.simplecaloriecalculator.Food

// объекты этого класса  используются для передачи объектов Product/Dish в адаптер
data class FoodToDisplay(var id: Long, var foodItem: Food, var portionEntered: Double) {

    var name: String = foodItem.name

    fun getProteinsPerPortion() = foodItem.getProteinsPer100() / 100 * portionEntered
    fun getFatsPerPortion() = foodItem.getFatsPer100() / 100 * portionEntered
    fun getCarbsPerPortion() = foodItem.getCarbsPer100() / 100 * portionEntered
    fun getCaloriesPerPortion() = foodItem.getCaloriesPer100() / 100 * portionEntered

}
