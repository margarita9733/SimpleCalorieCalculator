package com.hfad.simplecaloriecalculator.dishscreens.adddishscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.databinding.FragmentAddDishBinding
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishesViewModel
import java.util.*

class AddDishFragment(dish: Dish) : Fragment() {

    private var _binding: FragmentAddDishBinding? = null
    private val binding get() = _binding!!

    val dishToDisplay: Dish = dish

    lateinit var viewModel: AddDishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddDishBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application

        val dbInstance = CalcDatabase.getInstance(application)

        val dishDao = dbInstance.dishDao
        val dishProductDao = dbInstance.dishProductDao

        val viewModelFactory = AddDishViewModelFactory(dishDao, dishProductDao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(AddDishViewModel::class.java)

        binding.fabGoToSelectProductScreen.setOnClickListener {
        }

        binding.buttonAddDish.setOnClickListener {
            viewModel.addDish(saveDish())
            parentFragmentManager.popBackStack()
            val toast = Toast.makeText(context, "${dishToDisplay.name} ${dishToDisplay.id} added", Toast.LENGTH_SHORT).show()
        }

        binding.buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
            // viewModel.removeDish(dishToDisplay)
        }

        binding.editTextDishName.setText(dishToDisplay.name)
        binding.editTextDishPortion.setText(dishToDisplay.defaultPortionWeight.toString())
        binding.textDishProteins.setText(getString(R.string.proteins_letter_placeholder, (dishToDisplay.proteinsPerGram * 100).format()))
        binding.textDishFats.setText(getString(R.string.fats_letter_placeholder, (dishToDisplay.fatsPerGram * 100).format()))
        binding.textDishCarbs.setText(getString(R.string.carbs_letter_placeholder, (dishToDisplay.carbsPerGram * 100).format()))
        binding.textDishKcal.setText(getString(R.string.calories_placeholder, (dishToDisplay.caloriesPerGram * 100).format()))

        return view
    }


    fun saveDish(): Dish {
        val nameEntered = binding.editTextDishName.text.toString()
        val portionEntered = binding.editTextDishPortion.text.toString()

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

        var d: Dish = Dish(dishToDisplay.id, dIngs, dName, dPortion)

        return d
    }

    fun Double.format() = "%.2f".format(Locale.US, this)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}