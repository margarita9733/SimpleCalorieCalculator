package com.hfad.simplecaloriecalculator

data class Dish(
    override val id: Long = 0,
    var name: String = "",
    var ingredients: MutableMap<Product, Double> = mutableMapOf<Product, Double>() // MutableMap<Продукт, Вес>
) : Food {
    var defaultPortionWeight: Double = 100.0

    // var ingredients: MutableMap<Product, Double>
    // состав и БЖУК на список продуктов
    // список: ингредиент - количество ингредиента
    // ключ - ID ингредиента, значение - количество
    // количество - по весу в граммах
    // ! если и. упоминается второй раз - суммировать
    //fun proteinsPerGram() = ingredients.map { it.key.proteins }.sum() / ingredients.size


    val proteinsPerGram
        get() = ingredients.keys.sumOf { it.proteins } / ingredients.values.sumOf { it }

    val fatsPerGram
        get() = ingredients.keys.sumOf { it.fats } / ingredients.values.sumOf { it }

    val carbsPerGram
        get() = ingredients.keys.sumOf { it.carbs } / ingredients.values.sumOf { it }

   /* val caloriesPerGram
        get() = ingredients.keys.sumOf { it.calories * ingredients.entries[it].value } / ingredients.values.sumOf { it }*/

    val caloriesPerGram
        get() = ingredients.entries.sumOf{it.key.calories * it.value} / ingredients.values.sumOf{it}


    val proteinsPerPortion
        get() = proteinsPerGram * defaultPortionWeight

    val fatsPerPortion
        get() = fatsPerGram * defaultPortionWeight

    val carbsPerPortion
        get() = carbsPerGram * defaultPortionWeight

    val caloriesPerPortion
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