package com.example.simplecaloriecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {

    var products: List<Product> = listOf<Product>(
        Product("Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0),
        Product("Персик", 0.009, 0.001, 0.113, 0.46, 60.0),
        Product("Фундук", 0.16, 0.669, 0.09, 7.04, 20.0),
        Product("Сахар", 0.0, 0.0, 9.97, 3.98, 15.0),
        Product("Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),

        Product("Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),
        Product("Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),
        Product("Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),
        Product("Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0),
        Product("Чернослив", 0.023, 0.007, 0.57, 2.31, 30.0)
    )

    private var _food: MutableLiveData<List<Product>> = MutableLiveData(products)
    val food: LiveData<List<Product>> = _food


}