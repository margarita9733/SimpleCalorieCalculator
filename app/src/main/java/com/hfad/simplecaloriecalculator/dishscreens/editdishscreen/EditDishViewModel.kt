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

    fun removeIngredient(ingredient: Ingredient) {

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
        var dpEntities: List<DishProductEntity>
        var dishIngredients: MutableList<Ingredient> = mutableListOf()
        viewModelScope.launch {
            dishEntity = daoDish.getDishEntityByIdSync(dishId)
            dpEntities = daoDishProduct.getIngredientsByDishIdSync(dishId)
            for (item in dpEntities) dishIngredients.add(Ingredient(daoProduct.getProductByIdSync(item.productId), item.weight))

            _dish.value = Dish(
                dishEntity.id,
                dishIngredients.toList(),
                dishEntity.name,
                dishEntity.defaultPortionWeight
            )
        }
    }

    // все методы асинхронные в одной корутине, возвращается один готовый объект
    // как в с Dish в фрагменте
}
