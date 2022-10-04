package com.hfad.simplecaloriecalculator

import android.icu.util.UniversalTimeScale.toLong
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.hfad.simplecaloriecalculator.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonAddProduct.setOnClickListener {
            var i = createProduct()
            val toast = Toast.makeText(context, "${i.name} ${i.id} ${i.proteins}p " +
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

        val pId: Long = giveId()

        val p = Product(pId,pName,pProteins / 100,pFats / 100,pCarbs / 100,pKcal / 100,pPortion)

        return p
    }

    fun giveId(): Long = (viewModel.lastElementId() + 1).toLong()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
// как проверить, пустой editText или нет?
// когда он пустой, это не null  и не пустая строка
// инциализировать  переменную дефолтным значением,
// если окно не пустое - присвоить значение окна