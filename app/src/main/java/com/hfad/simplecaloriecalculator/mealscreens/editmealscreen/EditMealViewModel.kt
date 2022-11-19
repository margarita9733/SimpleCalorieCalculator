package com.hfad.simplecaloriecalculator.mealscreens.editmealscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import com.hfad.simplecaloriecalculator.mealscreens.FoodToDisplay

class EditMealViewModel : ViewModel() {
    private var products: List<Product> = listOf<Product>(
        Product(1, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
        Product(2, "Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
        Product(3, "Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
        Product(4, "Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
        Product(5, "Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),
        Product(6, "X", 0.023, 0.007, 0.57, 3.0, 30.0),
        Product(7, "Y", 0.023, 0.007, 0.57, 4.0, 30.0)
    )

    var ingsA: MutableList<Ingredient> = mutableListOf(
        Ingredient(products[0].id, products[0], 120.0),
        Ingredient(products[1].id, products[1], 30.0),
        /*Ingredient(products[5].id, products[5], 30.0),
        Ingredient(products[6].id, products[6], 30.0),*/
    )

    private var dishesToDisplay: List<Dish> = listOf<Dish>(
        Dish(0, ingsA, "a")
    )

    private var _meal: MutableLiveData<List<FoodToDisplay>> = MutableLiveData(
        listOf(
            FoodToDisplay(dishesToDisplay[0].id, dishesToDisplay[0], 100.0),
            FoodToDisplay(dishesToDisplay[0].id, dishesToDisplay[0], 200.0),
            FoodToDisplay(dishesToDisplay[0].id, dishesToDisplay[0], 300.0),

            FoodToDisplay(products[0].id, products[0], 100.0),
            FoodToDisplay(products[0].id, products[0], 200.0),
            FoodToDisplay(products[0].id, products[0], 300.0)
        )
    )

    val meal: LiveData<List<FoodToDisplay>> get() = _meal


//  сделать специальный класс и использовать его объекты для передачи объектов еды в адаптер
}

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