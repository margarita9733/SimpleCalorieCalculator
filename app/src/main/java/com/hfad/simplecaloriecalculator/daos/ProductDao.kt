package com.hfad.simplecaloriecalculator.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hfad.simplecaloriecalculator.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product): Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM product_table WHERE productId = :productId")
    fun get(productId: Long) : LiveData<Product>

    @Query("SELECT * FROM product_table ORDER BY productId DESC")
    fun getAll(): LiveData<List<Product>>

    @Delete
    suspend fun deleteAll(productList: List<Product>)

}