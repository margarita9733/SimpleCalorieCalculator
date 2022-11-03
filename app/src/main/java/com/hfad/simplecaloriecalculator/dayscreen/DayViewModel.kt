package com.hfad.simplecaloriecalculator.dayscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Food
import com.hfad.simplecaloriecalculator.Product

class DayViewModel : ViewModel() {
    private var products: List<Product> = listOf<Product>(
        Product(1, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
        Product(2, "Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
        Product(3, "Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
        Product(4, "Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
        Product(5, "Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),
    )

    var ingsA: MutableMap<Product, Double> = mutableMapOf<Product, Double>(
        products[0] to 120.0,
        products[1] to 30.0,
        products[4] to 10.0
    )

    var ingsB: MutableMap<Product, Double> = mutableMapOf<Product, Double>(
        products[0] to 120.0,
        products[2] to 40.0,
        products[4] to 10.0
    )

    var ingsC: MutableMap<Product, Double> = mutableMapOf<Product, Double>(
        products[0] to 120.0,
        products[3] to 20.0,
        products[4] to 10.0
    )

    private var dishesToDisplay: List<Dish> = listOf<Dish>(
        Dish(0, "dish1", 150.0, ingsA),
        Dish(1, "dish1", 150.0, ingsA),
        Dish(2, "dish1", 150.0, ingsA),

        Dish(3, "dish2", 120.0, ingsB), // default portions of each dish
        Dish(4, "dish2", 120.0, ingsB),
        Dish(5, "dish2", 120.0, ingsB),

        Dish(6, "dish3", 200.0, ingsC),
        Dish(7, "dish3", 200.0, ingsC),
        Dish(8, "dish3", 200.0, ingsC)
    )
                                // Map<Dish,PortionEntered>
    private var _meal: MutableLiveData<Map<Dish, Double>> = MutableLiveData(
        mapOf(
            dishesToDisplay[0] to 100.0,
            dishesToDisplay[1] to 200.0,
            dishesToDisplay[2] to 300.0,

            dishesToDisplay[3] to 100.0,
            dishesToDisplay[4] to 200.0,
            dishesToDisplay[5] to 300.0,

            dishesToDisplay[6] to 100.0,
            dishesToDisplay[7] to 200.0,
            dishesToDisplay[8] to 300.0
        )
    )
    val meal: LiveData<Map<Dish, Double>> get() = _meal

    /*fun removeDish(dish: Dish) {
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

    }*/

    //fun getTotalCaloriesConsumed() = sumOf{dishes.value?.forEach { it.caloriesPerGram * }}
}