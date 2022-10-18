package com.hfad.simplecaloriecalculator.productscreens.addproductscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.CalcDatabase
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
        val dao = CalcDatabase.getInstance(application).productDao
        val viewModelFactory = AddProductViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(AddProductViewModel::class.java)

        binding.buttonAddProduct.setOnClickListener {
            var i = createProduct()
            Toast.makeText(context, "${i.name} ${i.productId} ${i.proteins}p " +
                    " ${i.fats}f ${i.carbs}c ${i.calories}Kcal ${i.portionWeight}portion " +
                    "created ", Toast.LENGTH_LONG).show()

            viewModel.addToList(i)
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

       // val pId: Long = giveId()

        val p = Product(0,pName,pProteins / 100,pFats / 100,pCarbs / 100,pKcal / 100,pPortion)

        return p
    }

    //fun giveId(): Long = (viewModel.lastElementId() + 1).toLong()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
