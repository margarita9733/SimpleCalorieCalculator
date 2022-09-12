package com.hfad.simplecaloriecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {

    private var products: List<Product> = listOf<Product>(
        Product(0, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
        Product(1, "Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
        Product(2, "Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
        Product(3, "Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
        Product(4, "Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),

        Product(5, "Чернослив6", 0.021, 0.007, 0.57, 2.31, 30.0),
        Product(6, "Чернослив4", 0.022, 0.007, 0.57, 2.31, 30.0),
        Product(7, "Чернослив3", 0.026, 0.007, 0.57, 2.31, 30.0),
        Product(8, "Чернослив2", 0.024, 0.007, 0.57, 2.31, 30.0),
        Product(9, "Чернослив1", 0.025, 0.007, 0.57, 2.31, 30.0)
    )

    private var _food: MutableLiveData<List<Product>> = MutableLiveData(products)
    val food: LiveData<List<Product>> get() = _food



    fun removeFromList(product: Product) {
        var listToChange = products.toMutableList()
        val i = listToChange.indexOfFirst { it.id == product.id }
        listToChange.removeAt(i)
        products = listToChange.toList()
        _food.value = products
    }

}