package com.hfad.simplecaloriecalculator.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.database.entities.DishEntity

@Dao
interface DishDao {
    @Insert
    suspend fun insert(dishE: DishEntity): Long

    @Update
    suspend fun update(dishE: DishEntity)

    @Delete
    suspend fun delete(dishE: DishEntity)

    @Query("SELECT * FROM dish_table WHERE id = :dishId")
    fun getDishById(dishId: Long): LiveData<DishEntity>

    @Query("SELECT * FROM dish_table ORDER BY id DESC")
    fun getAll(): LiveData<List<DishEntity>>

    @Delete
    suspend fun deleteAll(dishList: List<DishEntity>)

    @Query("SELECT * FROM dish_table ORDER BY id DESC")
    suspend fun getAllSync(): List<DishEntity>

    @Query("SELECT * FROM dish_table WHERE id = :dishId")
    suspend fun getDishEntityByIdSync(dishId: Long): DishEntity


}