package com.hfad.simplecaloriecalculator.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity

@Dao
interface DishProductDao {
    @Insert
    suspend fun insert(dishProductE: DishProductEntity): Long

    @Update
    suspend fun update(dishProductE: DishProductEntity)

    @Update
    suspend fun updateAll(dishProductEntities: List<DishProductEntity>)

    @Delete
    suspend fun delete(dishProductE: DishProductEntity)

    @Query("SELECT * FROM dish_product_table ORDER BY id DESC")
    fun getAll(): LiveData<List<DishProductEntity>>

    @Query("SELECT * FROM dish_product_table  WHERE dish_id = :dishId ORDER BY id DESC")
    fun getDishProductsByDish(dishId: Long): LiveData<List<DishProductEntity>>

    @Delete
    suspend fun deleteAll(dishProductList: List<DishProductEntity>)

    @Insert
    suspend fun insertAll(dishProductList: List<DishProductEntity>)

    @Query("SELECT * FROM dish_product_table ORDER BY id DESC")
    suspend fun getAllSync(): List<DishProductEntity>

    @Query("SELECT * FROM dish_product_table WHERE dish_id = :dishToFindId ORDER BY id DESC")
    suspend fun getIngredientsByDishIdSync(dishToFindId: Long): List<DishProductEntity>
}