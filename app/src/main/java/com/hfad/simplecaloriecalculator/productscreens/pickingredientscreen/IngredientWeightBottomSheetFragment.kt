package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.simplecaloriecalculator.R

class IngredientWeightBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ingredient_weight_dialog, container, false)

    companion object {
        const val TAG = "Ingredient_Weight_Bottom_Sheet"
    }
}