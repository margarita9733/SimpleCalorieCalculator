package com.hfad.simplecaloriecalculator.productscreens.addproductscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.Repository
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import kotlinx.coroutines.launch

class AddProductViewModel(val dataBase: CalcDatabase) : ViewModel() {

    private val repository: Repository = Repository(dataBase)

    fun addProduct(product: Product) {
        viewModelScope.launch { repository.addProductToBase(product) }
        Log.i("AddProductVM", "product added")
    }

}