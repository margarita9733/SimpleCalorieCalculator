package com.hfad.simplecaloriecalculator.dayscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.databinding.FragmentDayBinding

class DayFragment : Fragment() {
    private var _binding: FragmentDayBinding? = null
    private val binding get() = _binding!!

    val defaultMaxCalories: Double = 1500.0
    val defaultCaloriesConsumed: Double = 2.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDayBinding.inflate(inflater,container,false)
        val view = binding.root

        var oldtext = binding.textCaloriesAvailable.getText().toString()
        binding.textCaloriesConsumedOfMax.text = getString(R.string.day_calories_consumed_of_max,defaultCaloriesConsumed.toString(),defaultMaxCalories.toString())
        binding.textCaloriesAvailable.text = getString(R.string.day_calories_available, defaultMaxCalories.toString())


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}