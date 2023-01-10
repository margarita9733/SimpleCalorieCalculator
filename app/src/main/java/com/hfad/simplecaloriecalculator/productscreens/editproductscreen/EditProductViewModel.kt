package com.hfad.simplecaloriecalculator.productscreens.editproductscreen

import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.Repository
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import kotlinx.coroutines.launch

class EditProductViewModel(val dataBase: CalcDatabase) : ViewModel() {

    private val repository: Repository = Repository(dataBase)

    fun updateProduct(product: Product) {
        viewModelScope.launch { repository.updateProductInBase(product) }
        Log.i("VM", "product updated ${product.id}")
    }
}