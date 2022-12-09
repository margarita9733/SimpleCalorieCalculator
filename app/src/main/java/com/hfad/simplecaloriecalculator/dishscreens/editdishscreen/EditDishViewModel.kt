package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.OnConflictStrategy
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.database.daos.DishDao
import com.hfad.simplecaloriecalculator.database.daos.DishProductDao
import com.hfad.simplecaloriecalculator.database.daos.ProductDao
import com.hfad.simplecaloriecalculator.database.entities.DishEntity
import com.hfad.simplecaloriecalculator.database.entities.DishProductEntity
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import kotlinx.coroutines.launch

class EditDishViewModel(
    val daoDish: DishDao,
    val daoDishProduct: DishProductDao,
    val daoProduct: ProductDao
) : ViewModel() {

    fun addDish(dish: Dish) {
        viewModelScope.launch {
            daoDish.insert(dishToDishEntity(dish))
            daoDishProduct.insertAll(dishToDPEntities(dish))
        }
        Log.i("AddDishVM", "dish added ${dish.id}")
    }

    fun updateDishEntity(dish: Dish) {
        viewModelScope.launch {
            daoDish.update(dishToDishEntity(dish))
        }
        Log.i("UpdDishVM", "dish updated ${dish.id}")
    }

    fun deleteDishEntity(dish: Dish) {
        viewModelScope.launch {
            daoDish.delete(dishToDishEntity(dish))
        }
    }


    fun insertDishProductEntities(dish: Dish) {
        viewModelScope.launch {
            daoDishProduct.insertAll(dishToDPEntities(dish))
        }
    }

    fun deleteDishProductEntities(dish: Dish) {
        viewModelScope.launch {
            daoDishProduct.deleteAll(dishToDPEntities(dish))
        }
    }

    //
    fun dishToDishEntity(dish: Dish)
            : DishEntity = DishEntity(dish.id, dish.name, dish.defaultPortionWeight)

    fun dishToDPEntities(dish: Dish): List<DishProductEntity> {
        val list: MutableList<DishProductEntity> = mutableListOf()
        for (i in dish.ingredients) {
            list.add(DishProductEntity(0, i.product.id, dish.id, i.portionEntered))
        }
        return list.toList()
    }

    //
// обращаться к бд внутри корутины за  сущностью для объекта логики
// внутри нее же преобразовывать в  объект логики и класть в ЛД, ЛД содержит тип объекта логики, фрагмент наблюдает за ЛД
    private val _dish = MutableLiveData<Dish?>(null)
    val dish: LiveData<Dish?> get() = _dish

    fun getDishById(dishId: Long) {
        var dishEntity: DishEntity
        viewModelScope.launch {
            dishEntity = daoDish.getDishEntityByIdSync(dishId)
            _dish.value = Dish(
                dishEntity.id,
                getIngredientsList(dishEntity.id),
                dishEntity.name,
                dishEntity.defaultPortionWeight
            )
        }
    }

    fun getIngredientsList(dishId: Long): List<Ingredient> {

        val ingsList: MutableList<Ingredient> = mutableListOf()
        val dPEntities: List<DishProductEntity> =
            daoDishProduct.getDishProductsByDish(dishId).value ?: listOf()

        for (entity in dPEntities) ingsList.add(getIngredient(entity))

        return ingsList.toList()
    }

    fun getIngredient(dishProductE: DishProductEntity): Ingredient {
        return Ingredient(
            (daoProduct.get(dishProductE.productId)).value ?: Product(),
            dishProductE.weight
        )
    }
    //
}