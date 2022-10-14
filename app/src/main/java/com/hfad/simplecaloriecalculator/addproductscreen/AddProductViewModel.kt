package com.hfad.simplecaloriecalculator.addproductscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.daos.ProductDao
import kotlinx.coroutines.launch

class AddProductViewModel(val dao: ProductDao) : ViewModel() {
    fun addToList(product: Product) {
        viewModelScope.launch { dao.insert(product) }
        Log.i("VM", "task added")
    }

}