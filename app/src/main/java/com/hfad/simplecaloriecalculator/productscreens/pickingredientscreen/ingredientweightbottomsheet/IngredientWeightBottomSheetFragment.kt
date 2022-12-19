package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen.ingredientweightbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.databinding.FragmentIngredientWeightDialogBinding
import com.hfad.simplecaloriecalculator.dishscreens.editdishscreen.EditDishFragment


class IngredientWeightBottomSheetFragment(val dishId: Long, val productId: Long) : BottomSheetDialogFragment() {

    private var _binding: FragmentIngredientWeightDialogBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: IngredientWeightBottomSheetViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIngredientWeightDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = CalcDatabase.getInstance(application).dishProductDao
        val viewModelFactory = IngredientWeightBottomSheetViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(IngredientWeightBottomSheetViewModel::class.java)

      //  binding.editTextIngredientWeight.text = productId. // place default product weight into editText, use vm to get it from db
        binding.buttonBack.setOnClickListener { this.dismiss() }
        binding.buttonOk.setOnClickListener {
            val weightEntered = binding.editTextIngredientWeight.text.toString()
            val iWeight = if (weightEntered == "") 0.0 else weightEntered.toDouble()
            viewModel.addIngredientToDish(dishId, productId, iWeight)
            dismiss()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditDishFragment(dishId, false), null)
                .setReorderingAllowed(true)
                .addToBackStack("edit_dish_show_screen")
                .commit()
        }

        return view
    }

    companion object {
        const val TAG = "Ingredient_Weight_Bottom_Sheet_Fragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

