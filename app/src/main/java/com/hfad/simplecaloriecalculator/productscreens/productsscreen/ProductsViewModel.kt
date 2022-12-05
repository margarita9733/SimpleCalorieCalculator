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
/*

private var products: List<Product> = listOf<Product>(
    Product(1, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
    Product(2, "Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
    Product(3, "Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
    Product(4, "Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
    Product(5, "Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),

    )*/
