package com.hfad.simplecaloriecalculator.editproductscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.daos.ProductDao

class EditProductViewModelFactory(private val dao: ProductDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProductViewModel::class.java)) {
            return EditProductViewModel(dao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}