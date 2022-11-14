package com.hfad.simplecaloriecalculator

data class Dish(
    override val id: Long = 0,
   override var name: String = "",
    var ingredients: MutableMap<Product, Double> = mutableMapOf<Product, Double>() // MutableMap<Продукт, Вес>
) : Food {
    var defaultPortionWeight: Double = 100.0

    // состав и БЖУК на список продуктов

    val proteinsPerGram
        get() = ingredients.entries.sumOf { it.key.proteins } / ingredients.values.sumOf { it }

    val fatsPerGram
        get() = ingredients.entries.sumOf { it.key.fats } / ingredients.values.sumOf { it }

    val carbsPerGram
        get() = ingredients.entries.sumOf { it.key.carbs } / ingredients.values.sumOf { it }

    val caloriesPerGram
        get() = ingredients.entries.sumOf{it.key.calories * it.value} / ingredients.values.sumOf{it}


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
        ingredients[product] = weight
    }

    fun removeIngredient(product: Product) {
        ingredients.remove(product)
    }
}