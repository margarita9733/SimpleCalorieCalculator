package com.hfad.simplecaloriecalculator

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var dishDao: DishDao
    private lateinit var db: CalcDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CalcDatabase::class.java
        ).build()
        dishDao = db.dishDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun writeDishAndReadInList() {
        val dish: DishEntity = TestUtil.createDish("testDish")
        dishDao.insert(dish)
        val byName: DishEntity = dishDao.getDishEntityByNameSync("testDish")
        assertThat(byName.name, equalTo(dish.name))
    }

    object TestUtil {
        fun createDish(name: String) = DishEntity(0, name)
    }

}