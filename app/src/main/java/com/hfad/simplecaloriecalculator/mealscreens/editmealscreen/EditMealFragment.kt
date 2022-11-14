package com.hfad.simplecaloriecalculator.mealscreens.editmealscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.simplecaloriecalculator.R
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.hfad.simplecaloriecalculator.databinding.FragmentEditMealBinding
import java.util.zip.Inflater


class EditMealFragment : Fragment() {

    private var _binding: FragmentEditMealBinding? = null
    private val binding get() = _binding!!

    val viewModel: EditMealViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditMealBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FoodItemAdapter()

        viewModel.meal.observe(viewLifecycleOwner, Observer {
            it?.let { it ->
                adapter.submitList(it)

                binding.textCalories.text = it.sumOf { it.getCaloriesPerPortion() }.toString()
                binding.textWeight.text = it.sumOf { it.portionEntered }.toString()
            }
        })
    }

}