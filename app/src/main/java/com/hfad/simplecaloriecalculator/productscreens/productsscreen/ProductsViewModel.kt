package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import android.util.Log
import androidx.lifecycle.*
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.Repository
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import com.hfad.simplecaloriecalculator.dishscreens.editdishscreen.EditDishFragment
import kotlinx.coroutines.launch

class ProductsViewModel(val dataBase: CalcDatabase) : ViewModel() {
    // before:  val food = dao.getAll()

    val repository: Repository = Repository(dataBase)

    private var _products: MutableLiveData<List<Product>?> = MutableLiveData(null)
    val products: LiveData<List<Product>?> get() = _products

    init {
        repository.productsBase.observeForever {
            it?.let {
                _products.value = it
                Log.i("productsVM", "products list updated")

            }
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch { repository.removeProductFromBase(product) }
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
