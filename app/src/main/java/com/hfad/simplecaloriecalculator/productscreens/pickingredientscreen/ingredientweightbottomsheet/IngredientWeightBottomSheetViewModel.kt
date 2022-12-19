package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen.ingredientweightbottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity
import kotlinx.coroutines.launch


class IngredientWeightBottomSheetViewModel(val dishProductDao: DishProductDao): ViewModel() {

    fun addIngredientToDish(dishId: Long, productId: Long, ingredientWeight: Double) {
        val dishProductEntity = DishProductEntity(0,dishId, productId, ingredientWeight)
        viewModelScope.launch { dishProductDao.insert(dishProductEntity) }
    }
}