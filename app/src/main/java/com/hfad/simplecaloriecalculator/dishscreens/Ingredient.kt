package com.hfad.simplecaloriecalculator.dishscreens

import com.hfad.simplecaloriecalculator.Product

data class Ingredient(var product: Product, var id: Long, var portionEntered: Double) {

    fun getProteinsPerPortion() = product.proteins * portionEntered
    fun getFatsPerPortion() = product.fats * portionEntered
    fun getCarbsPerPortion() = product.carbs * portionEntered
    fun getCaloriesPerPortion() = product.calories * portionEntered
}