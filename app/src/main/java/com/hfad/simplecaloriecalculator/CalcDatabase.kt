package com.hfad.simplecaloriecalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.simplecaloriecalculator.daos.ProductDao

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class CalcDatabase : RoomDatabase()  {
    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: CalcDatabase? = null

        fun getInstance(context: Context): CalcDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalcDatabase::class.java,
                        "calc_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}