package com.hfad.simplecaloriecalculator

class Meal(var time: Long = 0) {
    var id: Long = 0
    var food: MutableMap<Food, Double> = mutableMapOf()
}