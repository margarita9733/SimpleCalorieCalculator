package com.hfad.simplecaloriecalculator.dayscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.simplecaloriecalculator.R
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.hfad.simplecaloriecalculator.databinding.FragmentDayBinding
import com.hfad.simplecaloriecalculator.productscreens.productsscreen.ProductItemAdapter

class DayFragment : Fragment() {
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!

   private val viewModel: DayViewModel by activityViewModels()

    val defaultMaxCalories: Double = 1500.0
    val defaultCaloriesConsumed: Double = 2.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDayBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.textCaloriesConsumedOfMax.text = getString(R.string.day_calories_consumed_of_max,defaultCaloriesConsumed.toString(),defaultMaxCalories.toString())
        binding.textCaloriesAvailable.text = getString(R.string.day_calories_available, defaultMaxCalories.toString())



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MealItemAdapter()
        binding.mealsList.adapter = adapter
        viewModel.meal.observe(viewLifecycleOwner, Observer {
            it?.let { newMap ->
                adapter.submitList(newMap.map{it.key})
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}