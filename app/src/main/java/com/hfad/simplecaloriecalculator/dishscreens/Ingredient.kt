package com.hfad.simplecaloriecalculator.dishscreens

import com.hfad.simplecaloriecalculator.Product

data class Ingredient(var id: Long, var product: Product, var portionEntered: Double) {
    var name: String = product.name

    fun getProteinsPerPortion() = product.proteins * portionEntered
    fun getFatsPerPortion() = product.fats * portionEntered
    fun getCarbsPerPortion() = product.carbs * portionEntered
    fun getCaloriesPerPortion() = product.calories * portionEntered
}