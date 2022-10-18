package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.daos.ProductDao
import kotlinx.coroutines.launch

class FoodViewModel(val dao: ProductDao) : ViewModel() {

    /*private var products = dao.getAll().value ?: listOf<Product>()
    private var _food: MutableLiveData<List<Product>> = MutableLiveData(products)
    val food: LiveData<List<Product>> get() = _food*/
    val food = dao.getAll()

    fun addToList(product: Product) {
        viewModelScope.launch { dao.insert(product) }
        Log.i("VM", "product added")
    }

    fun removeFromList(product: Product) {
        viewModelScope.launch { dao.delete(product) }
    }

    fun clearBase(){
        viewModelScope.launch {
            val entries = dao.getAll().value
        if(entries != null) for (entry in entries){removeFromList(entry)}}
    }

    /*fun addToList(product: Product) {
        var listToChange = products.toMutableList()
        listToChange.add(product)
        products = listToChange.toList()
        _food.value = products
    }

    fun updateProduct(product: Product) {
        var listToChange = products.toMutableList()
        val productToUpdate = listToChange.indexOfFirst { it.productId == product.productId }
        listToChange[productToUpdate] = product
        products = listToChange.toList()
        _food.value = products
    }*/

    /*fun lastElementId(): Long {
        return if (products.lastIndex == -1) 0 else products[products.lastIndex].productId

    }*/
}






/*private var products: List<Product> = listOf<Product>(
        Product(1, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
        Product(2, "Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
        Product(3, "Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
        Product(4, "Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
        Product(5, "Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),

        Product(6, "Чернослив6", 0.021, 0.007, 0.57, 2.31, 30.0),
        Product(7, "Чернослив4", 0.022, 0.007, 0.57, 2.31, 30.0),
        Product(8, "Чернослив3", 0.026, 0.007, 0.57, 2.31, 30.0),
        Product(9, "Чернослив2", 0.024, 0.007, 0.57, 2.31, 30.0),
        Product(10, "Чернослив1", 0.025, 0.007, 0.57, 2.31, 30.0)
    )*/