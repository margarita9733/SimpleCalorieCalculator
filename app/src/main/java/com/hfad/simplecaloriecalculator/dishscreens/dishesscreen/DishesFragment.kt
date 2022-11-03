package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.databinding.FragmentDishesBinding
import androidx.lifecycle.ViewModelProvider


class DishesFragment : Fragment() {

    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!

    private var viewModel: DishesViewModel = ViewModelProvider(this).get(DishesViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}