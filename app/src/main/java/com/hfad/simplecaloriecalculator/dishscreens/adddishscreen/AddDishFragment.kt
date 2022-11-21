package com.hfad.simplecaloriecalculator.dishscreens.adddishscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.databinding.FragmentAddDishBinding
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishesViewModel
import java.util.*

class AddDishFragment(dish: Dish) : Fragment() {

    private var _binding: FragmentAddDishBinding? = null
    private val binding get() = _binding!!

    val dishToDisplay: Dish = dish

    val viewModel: DishesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddDishBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fabGoToSelectProductScreen.setOnClickListener {
//
        }

        binding.buttonAddDish.setOnClickListener {
            viewModel.updateDish(dishToDisplay)
        }



        binding.buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
            viewModel.removeDish(dishToDisplay)
        }

        binding.editTextDishName.setText(dishToDisplay.name)
        binding.editTextDishPortion.setText(dishToDisplay.defaultPortionWeight.toString())
        binding.textDishProteins.setText(getString(R.string.proteins_letter_placeholder, (dishToDisplay.proteinsPerGram * 100).format()))
        binding.textDishFats.setText(getString(R.string.fats_letter_placeholder, (dishToDisplay.fatsPerGram * 100).format()))
        binding.textDishCarbs.setText(getString(R.string.carbs_letter_placeholder, (dishToDisplay.carbsPerGram * 100).format()))
        binding.textDishKcal.setText(getString(R.string.calories_placeholder, (dishToDisplay.caloriesPerGram * 100).format()))

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun Double.format() = "%.2f".format(Locale.US, this)
}