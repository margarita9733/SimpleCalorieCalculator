package com.hfad.simplecaloriecalculator.editproductscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.CalcDatabase
import com.hfad.simplecaloriecalculator.productsscreen.FoodViewModel
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.FragmentEditProductBinding
import com.hfad.simplecaloriecalculator.productsscreen.FoodViewModelFactory
import java.util.*

class EditProductFragment(product: Product) : Fragment() {

    private var _binding: FragmentEditProductBinding? = null
    private val binding get() = _binding!!
    val productToDisplay: Product = product


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = CalcDatabase.getInstance(application).productDao
        val viewModelFactory = EditProductViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(EditProductViewModel::class.java)

        binding.editTextProductName.setText(productToDisplay.name)
        binding.editTextProductProteins.setText((productToDisplay.proteins * 100).format())
        binding.editTextProductFats.setText((productToDisplay.fats * 100).format())
        binding.editTextProductCarbs.setText((productToDisplay.carbs * 100).format())
        binding.editTextProductKcal.setText((productToDisplay.calories * 100).format())
        binding.editTextProductPortion.setText(productToDisplay.portionWeight.format())

        binding.buttonSaveChanges.setOnClickListener {
            viewModel.updateProduct(changeProduct())
            val toast = Toast.makeText(context, "changes saved", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        binding.buttonCancel.setOnClickListener { parentFragmentManager.popBackStack() }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun changeProduct(): Product {

        val nameEntered = binding.editTextProductName.getText().toString()
        val proteinsEntered = binding.editTextProductProteins.getText().toString()
        val fatsEntered = binding.editTextProductFats.getText().toString()
        val carbsEntered = binding.editTextProductCarbs.getText().toString()
        val kcalEntered = binding.editTextProductKcal.getText().toString()
        val portionEntered = binding.editTextProductPortion.getText().toString()

        val pName =
            when (nameEntered) {
                "" -> "Product"
                productToDisplay.name -> productToDisplay.name
                else -> nameEntered
            }

        val pProteins =
            when (proteinsEntered) {
                "" -> 0.0
                productToDisplay.proteins.toString() -> productToDisplay.proteins
                else -> proteinsEntered.toDouble() / 100
            }

        val pFats =
            when (fatsEntered) {
                "" -> 0.0
                productToDisplay.fats.toString() -> productToDisplay.fats
                else -> fatsEntered.toDouble() / 100
            }

        val pCarbs =
            when (carbsEntered) {
                "" -> 0.0
                productToDisplay.carbs.toString() -> productToDisplay.carbs
                else -> carbsEntered.toDouble() / 100
            }

        val pKcal =
            when (kcalEntered) {
                "" -> 0.0
                productToDisplay.calories.toString() -> productToDisplay.calories
                else -> kcalEntered.toDouble() / 100
            }

        val pPortion =
            when (portionEntered) {
                "" -> 100.0
                productToDisplay.portionWeight.toString() -> productToDisplay.portionWeight
                else -> portionEntered.toDouble()
            }
        return Product(productToDisplay.productId, pName, pProteins, pFats, pCarbs, pKcal, pPortion)
    }

    fun Double.format() = "%.2f".format(Locale.US,this)
    //val totalCaloriesPerHundredFormatted = "%.2f".format(totalCaloriesPerHundred)
    //Locale.US
}