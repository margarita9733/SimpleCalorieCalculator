package com.hfad.simplecaloriecalculator.productscreens.editproductscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.ProductDao

class EditProductViewModelFactory(private val dao: ProductDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProductViewModel::class.java)) {
            return EditProductViewModel(dao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}