package com.hfad.simplecaloriecalculator.productscreens.ingredientinfoscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.databinding.FragmentIngredientInfoDialogBinding
import com.hfad.simplecaloriecalculator.dishscreens.editdishscreen.EditDishFragment

class IngredientInfoBottomSheetDialogFragment(val dishId: Long, val productId: Long) : BottomSheetDialogFragment() {


    private var _binding: FragmentIngredientInfoDialogBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: IngredientInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIngredientInfoDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = CalcDatabase.getInstance(application).dishProductDao
        val viewModelFactory = IngredientInfoViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(IngredientInfoViewModel::class.java)

        binding.buttonSave.setOnClickListener {
            val weightEntered = binding.editTextIngredientWeight.text.toString()
            val iWeight = if (weightEntered == "") 0.0 else weightEntered.toDouble()
            viewModel.updateIngredientInDish(dishId, productId, iWeight)
            dismiss()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditDishFragment(dishId, false), null)
                .setReorderingAllowed(true)
                .addToBackStack("edit_dish_show_screen")
                .commit()
        }
        binding.buttonBack.setOnClickListener { dismiss() }

        return view
    }

    companion object {
        const val TAG = "Ingredient_Info_Bottom_Sheet_Fragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

