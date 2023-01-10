package com.hfad.simplecaloriecalculator.productscreens.addproductscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dataBase = CalcDatabase.getInstance(application)

        val viewModelFactory = AddProductViewModelFactory(dataBase)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(AddProductViewModel::class.java)

        binding.buttonAddProduct.setOnClickListener {

            viewModel.addProduct(createProduct())
            Toast.makeText(
                context, "product created ", Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.popBackStack()
        }

        binding.buttonCancel.setOnClickListener { parentFragmentManager.popBackStack() }

        return view
    }

    fun createProduct(): Product {

        val nameEntered = binding.editTextProductName.getText().toString()
        val proteinsEntered = binding.editTextProductProteins.getText().toString()
        val fatsEntered = binding.editTextProductFats.getText().toString()
        val carbsEntered = binding.editTextProductCarbs.getText().toString()
        val kcalEntered = binding.editTextProductKcal.getText().toString()
        val portionEntered = binding.editTextProductPortion.getText().toString()

        val pName = if (nameEntered == "") "Product" else nameEntered
        val pProteins = if (proteinsEntered == "") 0.0 else proteinsEntered.toDouble()
        val pFats = if (fatsEntered == "") 0.0 else fatsEntered.toDouble()
        val pCarbs = if (carbsEntered == "") 0.0 else carbsEntered.toDouble()
        val pKcal = if (kcalEntered == "") 0.0 else kcalEntered.toDouble()
        val pPortion = if (portionEntered == "") 100.0 else portionEntered.toDouble()

        val p = Product(0, pName, pProteins / 100, pFats / 100, pCarbs / 100, pKcal / 100, pPortion)

        return p
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
