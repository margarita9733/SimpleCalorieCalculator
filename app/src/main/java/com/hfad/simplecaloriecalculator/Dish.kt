package com.hfad.simplecaloriecalculator

data class Dish(
    var id: Long = 0,
    var name: String = "",
    var portionWeight: Double = 100.0,
    var ingredients: MutableMap<Product, Double> = mutableMapOf<Product, Double>()
) : Food {          // MutableMap<Продукт, Вес>

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

    val caloriesPerGram
        get() = ingredients.keys.sumOf { it.calories } / ingredients.values.sumOf { it }

    val proteinsPerPortion
        get() = proteinsPerGram * portionWeight

    val fatsPerPortion
        get() = fatsPerGram * portionWeight

    val carbsPerPortion
        get() = carbsPerGram * portionWeight

    val caloriesPerPortion
        get() = caloriesPerGram * portionWeight


    override fun getProteinsPer100(proteinsPerGram: Double): Double = proteinsPerGram * 100
    override fun getFatsPer100(fatsPerGram: Double): Double = fatsPerGram * 100
    override fun getCarbsPer100(carbsPerGram: Double): Double = carbsPerGram * 100
    override fun getCaloriesPer100(caloriesPerGram: Double): Double = caloriesPerGram * 100

    fun addIngredient(product: Product, weight: Double) {
        ingredients[product] = weight
    }

    fun removeIngredient(product: Product) {
        ingredients.remove(product)
    }
}