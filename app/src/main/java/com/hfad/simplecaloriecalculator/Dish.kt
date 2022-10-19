package com.hfad.simplecaloriecalculator

class Dish(
    var id: Long = 0,
    var name: String = "",
    var portionWeight: Double = 100.0,
    var ingredients: MutableMap<Product, Double> = mutableMapOf<Product, Double>()
) : Food {

    // var ingredients: MutableMap<Product, Double>
    // состав и БЖУК на список продуктов
    // список: ингредиент - количество ингредиента
    // ключ - ID ингредиента, значение - количество
    // количество - по весу в граммах
    // ! если и. упоминается второй раз - суммировать


    fun proteinsPerGram() = ingredients.map { it.key.proteins }.sum() / ingredients.size
    fun fatsPerGram() = ingredients.map { it.key.fats }.sum() / ingredients.size
    fun carbsPerGram() = ingredients.map { it.key.carbs }.sum() / ingredients.size
    fun caloriesPerGram() = ingredients.map { it.key.calories }.sum() / ingredients.size

    fun proteinsPerPortion() = proteinsPerGram() * portionWeight
    fun fatsPerPortion() = fatsPerGram() * portionWeight
    fun carbsPerPortion() = carbsPerGram() * portionWeight
    fun caloriesPerPortion() = caloriesPerGram() * portionWeight


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