package com.hfad.simplecaloriecalculator

import android.util.Log
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.ProductDao


class Repository(dataBase: CalcDatabase) {
    val productDao: ProductDao = dataBase.productDao
    val productsBase = productDao.getAll()

    suspend fun addProductToBase(product: Product) {
        productDao.insert(product)
        Log.i("VM", "product added")
    }

    suspend fun updateProductInBase(product: Product) {
        productDao.update(product)
    }

    suspend fun removeProductFromBase(product: Product) {
        productDao.delete(product)
    }

}