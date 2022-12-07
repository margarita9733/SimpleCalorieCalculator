package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao

class EditDishViewModelFactory(
    private val daoDish: DishDao,
    private val daoDishProduct: DishProductDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditDishViewModel::class.java)) {
            return EditDishViewModel(daoDish, daoDishProduct) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}