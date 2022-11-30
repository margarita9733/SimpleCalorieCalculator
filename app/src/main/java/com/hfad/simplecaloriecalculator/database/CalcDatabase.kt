package com.hfad.simplecaloriecalculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import com.hfad.simplecaloriecalculator.database.entities.*

@Database(
    entities = [
        Product::class,
        DishEntity::class,
        DishProductEntity::class,
        MealEntity::class,
        MealProductEntity::class,
        MealDishEntity::class
    ], version = 7, exportSchema = false
)
abstract class CalcDatabase : RoomDatabase() {
    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: CalcDatabase? = null

        fun getInstance(context: Context): CalcDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalcDatabase::class.java,
                        "calc_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}