package com.hfad.simplecaloriecalculator.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product): Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM product_table WHERE id = :productId")
    fun get(productId: Long) : LiveData<Product>

    @Query("SELECT * FROM product_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Product>>

    @Query("DELETE FROM product_table")
    fun deleteAllRecords()

    @Delete
    suspend fun deleteAll(productList: List<Product>)

    @Query("SELECT * FROM product_table WHERE id = :productToFindId ORDER BY id DESC")
    suspend fun getProductByIdSync(productToFindId: Long): Product

}