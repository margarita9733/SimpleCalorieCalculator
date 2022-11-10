package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product

class DishesViewModel: ViewModel() {

    private var products: List<Product> = listOf<Product>(
        Product(1, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
        Product(2, "Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
        Product(3, "Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
        Product(4, "Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
        Product(5, "Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),

    )

    var ings: MutableMap<Product, Double> = mutableMapOf<Product, Double>(
        products[0] to 120.0,
        products[1] to 30.0,
        products[4] to 10.0
    )

    private var dishesToDisplay: List<Dish> = listOf<Dish>(Dish(0, "dish1", ings))

    private var _dishes: MutableLiveData<List<Dish>> = MutableLiveData(dishesToDisplay)
    val dishes: LiveData<List<Dish>> get() = _dishes

    fun removeDish(dish: Dish) {
        var listToChange = dishesToDisplay.toMutableList()
        val i = listToChange.indexOfFirst { it.id == dish.id }
        listToChange.removeAt(i)
        dishesToDisplay = listToChange.toList()
        _dishes.value = dishesToDisplay
    }

    fun addDish(dish: Dish) {
        var listToChange = dishesToDisplay.toMutableList()
        listToChange.add(dish)
        dishesToDisplay = listToChange.toList()
        _dishes.value = dishesToDisplay
    }

    fun updateDish(dish: Dish) {
        var listToChange = dishesToDisplay.toMutableList()
        val dishToUpdate = listToChange.indexOfFirst { it.id == dish.id }
        listToChange[dishToUpdate] = dish
        dishesToDisplay = listToChange.toList()
        _dishes.value = dishesToDisplay
    }

    fun lastDishId(): Long {
        return if (dishesToDisplay.lastIndex == -1) 0 else dishesToDisplay[dishesToDisplay.lastIndex].id

    }
}