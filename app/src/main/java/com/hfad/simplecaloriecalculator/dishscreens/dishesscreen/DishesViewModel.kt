package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import android.util.Log
import androidx.lifecycle.*
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import kotlinx.coroutines.launch
import java.nio.file.Files.delete

class DishesViewModel(
    val dishDao: DishDao,
    val dishProductDao: DishProductDao,
    val productDao: ProductDao
) : ViewModel() {

    private val allDishEntities: LiveData<List<DishEntity>> = dishDao.getAll()
    private val allDishProductEntities: LiveData<List<DishProductEntity>> = dishProductDao.getAll()

    private var _dishes: MutableLiveData<List<Dish>?> = MutableLiveData(null)
    val dishes: LiveData<List<Dish>?> get() = _dishes

    init {
        allDishEntities.observeForever {
            it?.let {
                getAllDishes()
            }
        }
        allDishProductEntities.observeForever {
            it?.let {
                getAllDishes()
            }
        }
    }

    private val _triggerNavigationToEditDish = MutableLiveData<Long?>(null)
    val triggerNavigationToEditDish: LiveData<Long?> get() = _triggerNavigationToEditDish

    fun onNavigationEnded() {
        _triggerNavigationToEditDish.value = null
    }

    fun addDish(dish: Dish) {
        viewModelScope.launch {
            _triggerNavigationToEditDish.value = dishDao.insert(dishToDishEntity(dish))
        }
    }


    fun getAllDishes() {
        var dishEntities = this.allDishEntities.value ?: listOf()
        var dishesToReturn: MutableList<Dish> = mutableListOf()

        var dpEntitiesList: List<DishProductEntity> = listOf()
        var dishIngredients: MutableList<Ingredient> = mutableListOf()

        viewModelScope.launch {
            for (dishEntityItem in dishEntities) {
                dpEntitiesList = dishProductDao.getIngredientsByDishIdSync(dishEntityItem.id)
                for (dishProductEntityItem in dpEntitiesList) {
                    dishIngredients.add(Ingredient(productDao.getProductByIdSync(dishProductEntityItem.productId), dishProductEntityItem.weight))
                }
                dishesToReturn.add(
                    Dish(
                        dishEntityItem.id,
                        dishIngredients.toList(),
                        dishEntityItem.name,
                        dishEntityItem.defaultPortionWeight
                    )
                )
            }
            _dishes.value = dishesToReturn.toList()
        }
    }

    fun dishToDishEntity(dish: Dish)
            : DishEntity = DishEntity(dish.id, dish.name, dish.defaultPortionWeight)

    fun dishToDPEntities(dish: Dish): List<DishProductEntity> {
        val list: MutableList<DishProductEntity> = mutableListOf()
        for (i in dish.ingredients) {
            list.add(DishProductEntity(0, i.product.id, dish.id, i.portionEntered))
        }
        return list.toList()
    }

    fun removeDish(dish: Dish) {
        val dishEntityToDelete = dishToDishEntity(dish)
        val dishProductItemsToDelete = dishToDPEntities(dish)

        viewModelScope.launch {
            dishDao.delete(dishEntityToDelete)
            dishProductDao.deleteAll(dishProductItemsToDelete)
        }

    }
}




