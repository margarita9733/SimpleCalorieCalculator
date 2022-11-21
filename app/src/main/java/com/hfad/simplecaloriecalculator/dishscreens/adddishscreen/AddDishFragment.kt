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

class AddDishFragment : Fragment() {

    private var _binding: FragmentAddDishBinding? = null
    private val binding get() = _binding!!

    val viewModel: DishesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddDishBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fabGoToSelectProductScreen.setOnClickListener {

        }

        binding.buttonAddDish.setOnClickListener {
            //var d = createDish(id, name, list<Ingredient>)
            //viewModel.addDish()
        }



        binding.buttonCancel.setOnClickListener { /*parentFragmentManager.popBackStack()
            viewModel.removeDish(dishToDisplay)*/ }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // update instead of adding
    fun createDish(): Dish {
        var d = Dish(0)
        return d
    }


}