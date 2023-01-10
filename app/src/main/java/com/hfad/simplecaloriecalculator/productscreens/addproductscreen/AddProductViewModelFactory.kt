package com.hfad.simplecaloriecalculator.productscreens.addproductscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.ProductDao


class AddProductViewModelFactory(private val dataBase: CalcDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddProductViewModel::class.java)) {
            return AddProductViewModel(dataBase) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}