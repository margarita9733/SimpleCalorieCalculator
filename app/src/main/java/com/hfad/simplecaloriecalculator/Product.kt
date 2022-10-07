package com.hfad.simplecaloriecalculator

//БЖУК на 1 грамм продукта, вес порции в граммах
data class Product(
    var id: Long = 0,
    var name: String = "",
    var proteins: Double = 0.0,
    var fats: Double = 0.0,
    var carbs: Double = 0.0,
    var calories: Double = 0.0,
    var portionWeight: Double = 0.0
): Food {

    var proteinsPerPortion = proteins * portionWeight
    var fatsPerPortion = fats * portionWeight
    var carbsPerPortion = carbs * portionWeight
    var caloriesPerPortion =  calories * portionWeight

    override  fun getProteinsPer100(proteinsPerGram: Double): Double = proteins * 100

    override fun getFatsPer100(fatsPerGram: Double): Double = fats * 100

    override fun getCarbsPer100(carbsPerGram: Double): Double = carbs * 100

    override fun getCaloriesPer100(caloriesPerGram: Double): Double = calories * 100
}
