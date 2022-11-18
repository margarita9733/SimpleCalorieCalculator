package com.hfad.simplecaloriecalculator.dishscreens.editdishscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.R

class EditDishFragment(dish: Dish) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dish, container, false)
    }

}