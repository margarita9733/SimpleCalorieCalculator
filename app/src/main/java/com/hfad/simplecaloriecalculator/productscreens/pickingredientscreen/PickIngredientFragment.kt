package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.databinding.FragmentPickIngredientBinding
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishDeletionDialogFragment
import com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen.ingredientweightbottomsheet.IngredientWeightBottomSheetFragment

class PickIngredientFragment(var dishId: Long) : Fragment() {

    private var _binding: FragmentPickIngredientBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: PickIngredientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickIngredientBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = CalcDatabase.getInstance(application).productDao
        val viewModelFactory = PickIngredientViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(PickIngredientViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductAsIngItemAdapter { ingredient ->
            Toast.makeText(context, "item ${ingredient.name} ${ingredient.id} clicked", Toast.LENGTH_SHORT).show()

            val modalBottomSheet = IngredientWeightBottomSheetFragment(dishId, ingredient.id)
            modalBottomSheet.show(parentFragmentManager, IngredientWeightBottomSheetFragment.TAG)

        }

        binding.productsList.adapter = adapter
        viewModel.products.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showIngredientWeightDialog(ingredient: Ingredient) {
        val dialog = DishDeletionDialogFragment(
            onDeleteClicked = {
                // viewModel.removeIngredient(ingredient)
                val toast = Toast.makeText(context, "deleted an item: ${ingredient.name}, id ${ingredient.id} ", Toast.LENGTH_SHORT).show()
            },
            onDismissClicked = {
                parentFragmentManager.popBackStack()
            }
        )
        dialog.show(requireActivity().supportFragmentManager, "tag")
    }
}
/*parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditDishFragment(dishId, false), null)
                .setReorderingAllowed(true)
                .addToBackStack("edit_product_show_screen")
                .commit()*/