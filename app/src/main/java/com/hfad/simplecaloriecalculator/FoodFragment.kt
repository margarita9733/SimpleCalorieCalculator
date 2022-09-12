package com.hfad.simplecaloriecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.hfad.simplecaloriecalculator.databinding.FragmentFoodBinding


class FoodFragment : Fragment() {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel = FoodViewModel()


        val adapter = ProductItemAdapter { product ->
            viewModel.removeFromList(product)

        }
        binding.productsList.adapter = adapter

        viewModel.food.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
               // adapter.notifyDataSetChanged()
            }
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}