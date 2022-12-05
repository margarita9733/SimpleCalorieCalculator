package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch

class DishesViewModel(
    val dishDao: DishDao,
    val dishProductDao: DishProductDao,
    val productDao: ProductDao
) : ViewModel() {

    private val allDishEntities: LiveData<List<DishEntity>> = dishDao.getAll()

    private var _dishes: MutableLiveData<List<Dish>?> = MutableLiveData(null)
    val dishes: LiveData<List<Dish>?> get() = _dishes

    init {
        allDishEntities.observeForever {
            it?.let {
                _dishes.value = getAllDishes()
            }
        }
    }


    /*Vm.food.observe(viewLifecycleOwner, Observer {
        it?.let {
            adapter.submitList(it)
        }
    }*/

    /*Vm.food.observe(viewLifecycleOwner, Observer {
        it?.let {
            adapter.submitList(it)
        }
    }*/


    fun getAllDishes(): List<Dish> {  // как во всех этих методах
        // будет работать LD для уведомления?
        Log.i("DishesVM", "getAllDishes start")

        var dishesToReturn: MutableList<Dish> = mutableListOf()
        val dishEntities: List<DishEntity> = dishDao.getAll().value ?: listOf()

        for (dishEntity in dishEntities) {
            dishesToReturn.add(getDish(dishEntity))
        }
        return dishesToReturn.toList()
    }

    fun getDish(dishEntity: DishEntity): Dish {
        return Dish(
            dishEntity.id,
            getIngredientsList(dishEntity.id),
            dishEntity.name,
            dishEntity.defaultPortionWeight
        )
    }

    fun getIngredientsList(dishId: Long): List<Ingredient> {

        val ingsList: MutableList<Ingredient> = mutableListOf()
        val dPEntities: List<DishProductEntity> =
            dishProductDao.getDishProductsByDish(dishId).value ?: listOf()

        for (entity in dPEntities) ingsList.add(getIngredient(entity))

        return ingsList.toList()
    }

    fun getIngredient(dishProductE: DishProductEntity): Ingredient {
        return Ingredient(
            (productDao.get(dishProductE.productId)).value ?: Product(),
            dishProductE.weight
        )
    }
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


