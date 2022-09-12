package com.hfad.simplecaloriecalculator

//БЖУК на 1 грамм продукта, вес порции в граммах
data class Product(
    val id: Long = 0,
    val name: String = "",
    val proteins: Double = 0.0,
    val fats: Double = 0.0,
    val carbs: Double = 0.0,
    val calories: Double = 0.0,
    val portionWeight: Double = 0.0
) {

    val proteinsPerPortion = proteins * portionWeight
    val fatsPerPortion = fats * portionWeight
    val carbsPerPortion = carbs * portionWeight
    val caloriesPerPortion =  calories * portionWeight
}
