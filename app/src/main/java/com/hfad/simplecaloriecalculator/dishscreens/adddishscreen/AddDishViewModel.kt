package com.hfad.simplecaloriecalculator.dishscreens.adddishscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity
import kotlinx.coroutines.launch

class AddDishViewModel(
    val daoDish: DishDao,
    val daoDishProduct: DishProductDao
) : ViewModel() {

    fun addDish(dish: Dish) {
        viewModelScope.launch {
            daoDish.insert(dishToDishEntity(dish))
            daoDishProduct.insertAll(dishToDPEntities(dish))
        }
        Log.i("AddDishVM", "dish added")
    }

    fun dishToDishEntity(dish: Dish)
            : DishEntity = DishEntity(dish.id, dish.name, dish.defaultPortionWeight)

    fun dishToDPEntities(dish: Dish): List<DishProductEntity> {
        val list: MutableList<DishProductEntity> = mutableListOf()
        for (i in dish.ingredients) {
            list.add(DishProductEntity(0, i.product.id, dish.id, i.portionEntered))
        }
        return list.toList()
    }
}


