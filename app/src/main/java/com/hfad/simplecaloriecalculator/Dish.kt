package com.hfad.simplecaloriecalculator

import com.hfad.simplecaloriecalculator.dishscreens.Ingredient

data class Dish(
    override val id: Long = 0,
    var ingredients: MutableList<Ingredient> = mutableListOf(), // mutable для работы на VM, при добавлении бд поменять на immutable
    override var name: String = "Блюдо $id"
) : Food {
    var defaultPortionWeight: Double = 100.0

    // состав и БЖУК на список продуктов

    val proteinsPerGram
        get() = ingredients.sumOf { it.product.proteins * it.portionEntered } / ingredients.sumOf { it.portionEntered }

    val fatsPerGram
        get() = ingredients.sumOf { it.product.fats * it.portionEntered } / ingredients.sumOf { it.portionEntered }

    val carbsPerGram
        get() = ingredients.sumOf { it.product.carbs * it.portionEntered } / ingredients.sumOf { it.portionEntered }

    val caloriesPerGram
        get() = ingredients.sumOf { it.product.calories * it.portionEntered } / ingredients.sumOf { it.portionEntered }


    val proteinsPerDefaultPortion
        get() = proteinsPerGram * defaultPortionWeight

    val fatsPerDefaultPortion
        get() = fatsPerGram * defaultPortionWeight

    val carbsPerDefaultPortion
        get() = carbsPerGram * defaultPortionWeight

    val caloriesPerDefaultPortion
        get() = caloriesPerGram * defaultPortionWeight

    override fun getProteinsPer100(): Double = proteinsPerGram * 100
    override fun getFatsPer100(): Double = fatsPerGram * 100
    override fun getCarbsPer100(): Double = carbsPerGram * 100
    override fun getCaloriesPer100(): Double = caloriesPerGram * 100

    fun addIngredient(product: Product, weight: Double) {
        // ingredients[product] = weight
    }

    fun removeIngredient(product: Product) {
        // ingredients.remove(product)
    }
}