package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.OnConflictStrategy
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity
import kotlinx.coroutines.launch

class EditDishViewModel(
     val daoDish: DishDao,
     val daoDishProduct: DishProductDao
) : ViewModel() {

    fun updateDishEntity(dish: Dish) {
        viewModelScope.launch {
            daoDish.update(dishToDishEntity(dish))
        }
        Log.i("UpdDishVM", "dish updated ${dish.id}")
    }

    fun insertDishProductEntities(dish: Dish){
        viewModelScope.launch {
            daoDishProduct.insertAll(dishToDPEntities(dish))
        }
    }

    fun deleteDishProductEntities(dish: Dish) {
        viewModelScope.launch {
            daoDishProduct.deleteAll(dishToDPEntities(dish))
        }
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