package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.R
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
        binding.editTextDishPortion.setText(dishToDisplay.defaultPortionWeight.toString())
        binding.textDishProteins.setText(getString(R.string.proteins_letter_placeholder, (dishToDisplay.proteinsPerGram * 100).format()))
        binding.textDishFats.setText(getString(R.string.fats_letter_placeholder, (dishToDisplay.fatsPerGram * 100).format()))
        binding.textDishCarbs.setText(getString(R.string.carbs_letter_placeholder, (dishToDisplay.carbsPerGram * 100).format()))
        binding.textDishKcal.setText(getString(R.string.calories_placeholder, (dishToDisplay.caloriesPerGram * 100).format()))

       /* binding.buttonSaveChanges.setOnClickListener {
            viewModel.updateDish(changeDish())
            val toast = Toast.makeText(context, "changes saved", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }*/

        binding.buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun changeDish(): Dish {
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


        var d: Dish = Dish(dishToDisplay.id, dIngs, dName)
        d.defaultPortionWeight = dPortion
        return d
    }

    fun Double.format() = "%.2f".format(Locale.US, this)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}