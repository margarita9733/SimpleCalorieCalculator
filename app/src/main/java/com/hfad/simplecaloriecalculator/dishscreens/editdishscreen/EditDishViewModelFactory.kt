package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.daos.ProductDao

class EditDishViewModelFactory(
    private val daoDish: DishDao,
    private val daoDishProduct: DishProductDao,
    private val daoProduct: ProductDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditDishViewModel::class.java)) {
            return EditDishViewModel(daoDish, daoDishProduct, daoProduct) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}