package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import com.hfad.simplecaloriecalculator.productscreens.productsscreen.ProductsViewModel

class PickIngredientViewModelFactory(private val dao: ProductDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PickIngredientViewModel::class.java)) {
            return PickIngredientViewModel(dao) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}
