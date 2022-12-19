package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen.ingredientweightbottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao

class IngredientWeightBottomSheetViewModelFactory(private val dao: DishProductDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientWeightBottomSheetViewModel::class.java)) {
            return IngredientWeightBottomSheetViewModel(dao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}