package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.ProductDao

class ProductsViewModelFactory(private val dataBase: CalcDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(dataBase) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}