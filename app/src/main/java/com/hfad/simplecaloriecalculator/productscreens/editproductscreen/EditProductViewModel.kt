package com.hfad.simplecaloriecalculator.productscreens.editproductscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.daos.ProductDao
import kotlinx.coroutines.launch

class EditProductViewModel(val dao: ProductDao) : ViewModel() {
    fun updateProduct(product: Product) {
        viewModelScope.launch { dao.update(product) }
        Log.i("VM", "product updated ${product.productId}")
    }
}