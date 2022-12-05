package com.hfad.simplecaloriecalculator.dishscreens

import com.hfad.simplecaloriecalculator.Product

data class Ingredient(var product: Product, var portionEntered: Double) {
    val name
        get() = product.name

    val id
        get() = product.id

    fun getProteinsPerPortion() = product.proteins * portionEntered
    fun getFatsPerPortion() = product.fats * portionEntered
    fun getCarbsPerPortion() = product.carbs * portionEntered
    fun getCaloriesPerPortion() = product.calories * portionEntered
}