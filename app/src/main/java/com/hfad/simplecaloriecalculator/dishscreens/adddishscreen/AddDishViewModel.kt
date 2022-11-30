package com.hfad.simplecaloriecalculator.dishscreens.adddishscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Dish

class AddDishViewModel() : ViewModel() {
    fun addDish(dish: Dish) {
/*dishDao.insert(dishToDishEntity(dish))

* */
    }
}
/*
* fun dishToDishEntity(dish:Dish): DishEntity = DishEntity(dish.id, dish.name, dish.defaultPortionWeight)
* dishDao.insert(dishToDishEntity(dish))
*
* fun dishToDishProductEntitiesList(dish:Dish): List<DishProductEntity> {
* val list = dish.ingredients.map{it -> }
* return listOf()}
*
*
* */