package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.daos.ProductDao

class FoodViewModelFactory(private val dao: ProductDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(dao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}