package com.hfad.simplecaloriecalculator.dishscreens.adddishscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao

class AddDishViewModelFactory(
    private val daoDish: DishDao,
    private val daoDishProduct: DishProductDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddDishViewModel::class.java)) {
            return AddDishViewModel(daoDish, daoDishProduct) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}