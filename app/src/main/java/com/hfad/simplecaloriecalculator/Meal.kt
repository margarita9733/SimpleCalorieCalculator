package com.hfad.simplecaloriecalculator

class Meal(
    var id: Long = 0,
    var time: Long = 0,
    var food: MutableMap<Food, Double> = mutableMapOf()
) {
    //var calories get() =
}