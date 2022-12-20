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
    // private val allDishProductEntities: LiveData<List<DishProductEntity>> = dishProductDao.getAll()

    private var _dishes: MutableLiveData<List<Dish>?> = MutableLiveData(null)
    val dishes: LiveData<List<Dish>?> get() = _dishes

    /*  private var _dishes_contents: MutableLiveData<List<DishProductEntity>?> = MutableLiveData(null)
      val dishes_contents: LiveData<List<DishProductEntity>?> get() = _dishes_contents
  */
    init {
        allDishEntities.observeForever {
            it?.let {
                _dishes.value = getAllDishes(it)
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

    // перенести в Use Case \/
    fun getAllDishes(dishEntities: List<DishEntity>): List<Dish> {
        Log.i("DishesVM", "getAllDishes start")

        var dishesToReturn: MutableList<Dish> = mutableListOf()
        //val dishEntities: List<DishEntity> = dishDao.getAll().value ?: listOf()

        for (dishEntity in dishEntities) {
            dishesToReturn.add(getDish(dishEntity))
        }
        return dishesToReturn.toList()
    }

    fun getDish(dishEntity: DishEntity): Dish {
        return Dish(
            dishEntity.id,
            getIngredientsList(dishEntity.id),
            dishEntity.name,
            dishEntity.defaultPortionWeight
        )
    }

    fun getIngredientsList(dishId: Long): List<Ingredient> {

        val ingsList: MutableList<Ingredient> = mutableListOf()
        val dPEntities: List<DishProductEntity> =
            dishProductDao.getDishProductsByDish(dishId).value ?: listOf()

        for (entity in dPEntities) ingsList.add(getIngredient(entity))

        return ingsList.toList()
    }

    fun getIngredient(dishProductE: DishProductEntity): Ingredient {
        return Ingredient(
            (productDao.get(dishProductE.productId)).value ?: Product(),
            dishProductE.weight
        )
    }
    // ^перенести в UseCase
    ////////////////////////////////

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



