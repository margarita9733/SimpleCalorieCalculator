package com.hfad.simplecaloriecalculator

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient

data class Dish(
    override val id: Long = 0,
    var ingredients: List<Ingredient> = listOf(),
    override var name: String = "Блюдо $id"
) : Food {
    var defaultPortionWeight: Double = 100.0

    // состав и БЖУК на список продуктов

    val proteinsPerGram
        get() = if (ingredients.isEmpty()) 0.0 else ingredients.sumOf { it.product.proteins * it.portionEntered } / ingredients.sumOf { it.portionEntered }

    val fatsPerGram
        get() = if (ingredients.isEmpty()) 0.0 else ingredients.sumOf { it.product.fats * it.portionEntered } / ingredients.sumOf { it.portionEntered }

    val carbsPerGram
        get() = if (ingredients.isEmpty()) 0.0 else ingredients.sumOf { it.product.carbs * it.portionEntered } / ingredients.sumOf { it.portionEntered }

    val caloriesPerGram
        get() = if (ingredients.isEmpty()) 0.0 else ingredients.sumOf { it.product.calories * it.portionEntered } / ingredients.sumOf { it.portionEntered }


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
}

/*старый класс со списком + отдельный аналогичный класс для entity без списка
* старый испольуется в слое отображения,
* аналогичный класс-entity без списка используется в работе с бд
* один преобразуется в другой, преобразует классы специальный UseCase-класс*/
