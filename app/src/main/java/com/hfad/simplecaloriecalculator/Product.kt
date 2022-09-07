package com.hfad.simplecaloriecalculator

//БЖУК на 1 грамм продукта, вес порции в граммах
data class Product(
    var proteins: Int = 0,
    var fats: Int = 0,
    var carbs: Int = 0,
    var calories: Int =0,
    var portionWeight: Int = 0
) {
    fun proteinsPerPortion(): Int = proteins * portionWeight
    fun fatsPerPortion(): Int = fats * portionWeight
    fun carbsPerPortion() :Int = carbs * portionWeight
    fun caloriesPerPortion(): Int = calories * portionWeight
}  