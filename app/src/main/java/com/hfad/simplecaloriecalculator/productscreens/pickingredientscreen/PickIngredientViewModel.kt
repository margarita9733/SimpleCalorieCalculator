package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen

import androidx.lifecycle.ViewModel
import com.hfad.simplecaloriecalculator.database.daos.ProductDao

class PickIngredientViewModel(val productDao: ProductDao) : ViewModel() {

    val products = productDao.getAll()



}
