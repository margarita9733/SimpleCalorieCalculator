package com.hfad.simplecaloriecalculator.productscreens.ingredientinfoscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen.ingredientweightbottomsheet.IngredientWeightBottomSheetViewModel


class IngredientInfoViewModelFactory(private val dao: DishProductDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientInfoViewModel::class.java)) {
            return IngredientInfoViewModel(dao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}