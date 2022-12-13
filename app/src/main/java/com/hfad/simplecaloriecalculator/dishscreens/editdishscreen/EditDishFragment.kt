package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.databinding.FragmentEditDishBinding
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import com.hfad.simplecaloriecalculator.dishscreens.IngredientItemAdapter
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishDeletionDialogFragment
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishesFragment
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishesViewModel
import com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen.PickIngredientFragment
import kotlinx.coroutines.launch
import java.util.*

class EditDishFragment(private val dishId: Long, private val dishIsNew: Boolean) : Fragment() {

    private var _binding: FragmentEditDishBinding? = null
    private val binding get() = _binding!!


    lateinit var viewModel: EditDishViewModel
    lateinit var dishToDisplay: Dish

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditDishBinding.inflate(inflater, container, false)
        var view = binding.root

        val application = requireNotNull(this.activity).application
        val dbInstance = CalcDatabase.getInstance(application)
        val dishDao = dbInstance.dishDao
        val dishProductDao = dbInstance.dishProductDao
        val productDao = dbInstance.productDao
        val viewModelFactory = EditDishViewModelFactory(dishDao, dishProductDao, productDao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(EditDishViewModel::class.java)

        val adapter = IngredientItemAdapter({ ingredient ->
            showIngredientDeletionDialog(ingredient)
        }, { dish ->
            /* parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditDishFragment(dish.id, false), null)
                .setReorderingAllowed(true)
               .addToBackStack("edit_dish_show_screen")
               .commit()*/
        })

        binding.ingredientsList.adapter = adapter

        viewModel.getDishById(dishId)
        viewModel.dish.observeForever {
            it?.let {
                dishToDisplay = it
                binding.editTextDishName.setText(dishToDisplay.name)
                binding.editTextDishPortion.setText(dishToDisplay.defaultPortionWeight.toString())
                binding.textDishProteins.setText(getString(R.string.proteins_letter_placeholder, (dishToDisplay.proteinsPerGram * 100).format()))
                binding.textDishFats.setText(getString(R.string.fats_letter_placeholder, (dishToDisplay.fatsPerGram * 100).format()))
                binding.textDishCarbs.setText(getString(R.string.carbs_letter_placeholder, (dishToDisplay.carbsPerGram * 100).format()))
                binding.textDishKcal.setText(getString(R.string.calories_placeholder, (dishToDisplay.caloriesPerGram * 100).format()))
                adapter.submitList(it.ingredients)
            }
        }

        binding.buttonSaveChanges.setOnClickListener {
            val updatedDish = changeDish(dishToDisplay)
            viewModel.updateDishEntity(updatedDish)                 //
            viewModel.deleteDishProductEntities(dishToDisplay)      // обновить таблицы в бд
            viewModel.insertDishProductEntities(updatedDish)        //
            val toast = Toast.makeText(context, "changes saved oldId${dishToDisplay.id} newId${updatedDish.id}", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        binding.buttonCancel.setOnClickListener {
            if (dishIsNew) {
                viewModel.deleteDishEntity(dishToDisplay)
                parentFragmentManager.popBackStack()
            } else {
                parentFragmentManager.popBackStack()
            }
        }

        binding.fabGoToSelectProductScreen.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, PickIngredientFragment(dishToDisplay.id), null)
                .setReorderingAllowed(true)
                .addToBackStack("edit_dish_show_screen")
                .commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // rw adapter
        /*
             val adapter = IngredientsListItemAdapter({ ingredient ->
            showIngredientDeletionDialog(ingredient)
        }, { dish ->
           // parentFragmentManager.beginTransaction()
            //    .replace(R.id.fragment_container_view, EditDishFragment(dish.id, false), null)
            //    .setReorderingAllowed(true)
             //   .addToBackStack("edit_dish_show_screen")
             //   .commit()
        })

        binding.ingredientsList.adapter = adapter
        viewModel.dish.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.ingredients)                 ^^^^^
            }
        })*/
    }

    fun changeDish(dish: Dish): Dish {
        val nameEntered = binding.editTextDishName.text.toString()
        val portionEntered = binding.editTextDishPortion.getText().toString()

        val dName =
            when (nameEntered) {
                "" -> "Dish ${dishToDisplay.id}"
                dishToDisplay.name -> dishToDisplay.name
                else -> nameEntered
            }
        val dPortion =
            when (portionEntered) {
                "" -> 100.0
                dishToDisplay.defaultPortionWeight.toString() -> dishToDisplay.defaultPortionWeight
                else -> portionEntered.toDouble()
            }
        val dIngs: List<Ingredient> = listOf()

        dish.name = dName
        dish.defaultPortionWeight = dPortion
        dish.ingredients = dIngs
        return dish
    }

    fun Double.format() = "%.2f".format(Locale.US, this)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showIngredientDeletionDialog(ingredient: Ingredient) {
        val dialog = DishDeletionDialogFragment(
            onDeleteClicked = {
                viewModel.removeIngredient(ingredient)
                val toast = Toast.makeText(context, "deleted an item: ${ingredient.name}, id ${ingredient.id} ", Toast.LENGTH_SHORT).show()
            },
            onDismissClicked = {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, DishesFragment(), null)
                    .setReorderingAllowed(true)
                   .addToBackStack("edit_dish_show_screen")
                   .commit()
            }
        )
        dialog.show(requireActivity().supportFragmentManager, "tag")
    }
}