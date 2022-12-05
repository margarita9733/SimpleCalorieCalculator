package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.daos.ProductDao


class DishesViewModelFactory(
    private val dishDao: DishDao,
    private val dishProductDao: DishProductDao,
    private val productDao: ProductDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DishesViewModel::class.java)) {
            return DishesViewModel(dishDao, dishProductDao, productDao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}

