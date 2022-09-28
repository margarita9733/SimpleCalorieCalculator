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
) {

    var proteinsPerPortion = proteins * portionWeight
    var fatsPerPortion = fats * portionWeight
    var carbsPerPortion = carbs * portionWeight
    var caloriesPerPortion =  calories * portionWeight
}
