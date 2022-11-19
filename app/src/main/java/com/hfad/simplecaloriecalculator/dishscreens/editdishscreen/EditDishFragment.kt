package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.databinding.FragmentEditDishBinding
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishesViewModel
import java.util.*

class EditDishFragment(dish: Dish) : Fragment() {

    private var _binding: FragmentEditDishBinding? = null
    private val binding get() = _binding!!

    val dishToDisplay: Dish = dish

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditDishBinding.inflate(inflater, container, false)
        var view = binding.root

        val viewModel: DishesViewModel by activityViewModels()

        binding.editTextDishName.setText(dishToDisplay.name)
        binding.editTextDishPortion.setText(dishToDisplay.defaultPortionWeight.format().toString())
        binding.textDishProteins.setText((dishToDisplay.proteinsPerGram * 100).format().toString())
        binding.textDishFats.setText((dishToDisplay.fatsPerGram * 100).format().toString())
        binding.textDishCarbs.setText((dishToDisplay.carbsPerGram * 100).format().toString())
        binding.textDishKcal.setText((dishToDisplay.caloriesPerGram * 100).format().toString())

        binding.buttonSaveChanges.setOnClickListener {
            viewModel.updateDish(changeDish())
            val toast = Toast.makeText(context, "changes saved", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        binding.buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
            viewModel.removeDish(dishToDisplay)
        }

        return view
    }

    fun changeDish(): Dish {
        val nameEntered = binding.editTextDishName.text.toString()
        val proteinsEntered = binding.textDishProteins.text.toString()
        val fatsEntered = binding.textDishFats.text.toString()
        val carbsEntered = binding.textDishCarbs.text.toString()
        val kcalEntered = binding.textDishKcal.text.toString()
        val portionEntered = binding.editTextDishPortion.text.toString()

        val dName =
            when (nameEntered) {
                "" -> "Dish ${dishToDisplay.id}"
                dishToDisplay.name -> dishToDisplay.name
                else -> nameEntered
            }

        val dProteins =
            when (proteinsEntered) {
                "" -> 0.0
                dishToDisplay.proteinsPerGram.toString() -> dishToDisplay.proteinsPerGram
                else -> proteinsEntered.toDouble() / 100
            }

        val dFats =
            when (fatsEntered) {
                "" -> 0.0
                dishToDisplay.fatsPerGram.toString() -> dishToDisplay.fatsPerGram
                else -> fatsEntered.toDouble() / 100
            }

        val pCarbs =
            when (carbsEntered) {
                "" -> 0.0
                dishToDisplay.carbsPerGram.toString() -> dishToDisplay.carbsPerGram
                else -> carbsEntered.toDouble() / 100
            }

        val dKcal =
            when (kcalEntered) {
                "" -> 0.0
                dishToDisplay.caloriesPerGram.toString() -> dishToDisplay.caloriesPerGram
                else -> kcalEntered.toDouble() / 100
            }

        val dPortion =
            when (portionEntered) {
                "" -> 100.0
                dishToDisplay.defaultPortionWeight.toString() -> dishToDisplay.defaultPortionWeight
                else -> portionEntered.toDouble()
            }

        val dIngs: MutableList<Ingredient> = mutableListOf()
        var d: Dish = Dish(dishToDisplay.id, dIngs, dName)
        //d.fatsPerGram = dFats
        return d
    }

    fun Double.format() = "%.2f".format(Locale.US, this)
}