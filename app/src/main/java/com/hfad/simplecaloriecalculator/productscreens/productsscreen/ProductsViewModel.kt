package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import kotlinx.coroutines.launch

class ProductsViewModel(val dao: ProductDao) : ViewModel() {

    val food = dao.getAll()

    fun addToList(product: Product) {
        viewModelScope.launch { dao.insert(product) }
        Log.i("VM", "product added")
    }

    fun removeFromList(product: Product) {
        viewModelScope.launch { dao.delete(product) }
    }

    fun clearBase() {
        viewModelScope.launch {
            val entries = dao.getAll().value
            if (entries != null) for (entry in entries) {
                removeFromList(entry)
            }
        }
    }
}