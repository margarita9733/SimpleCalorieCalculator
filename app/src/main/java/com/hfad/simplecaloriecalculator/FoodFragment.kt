package com.hfad.simplecaloriecalculator

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hfad.simplecaloriecalculator.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fabGoToAddScreen.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, AddProductFragment::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("add_product_show_screen")
                .commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ProductItemAdapter({ product ->
            showProductDeletionDialog(product)
        }, {product ->
            Toast.makeText(context, "item ${product.name} ${product.id} clicked", Toast.LENGTH_SHORT).show()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditProductFragment(product), null)
                .setReorderingAllowed(true)
                .addToBackStack("edit_product_show_screen")
                .commit()
        })

        binding.productsList.adapter = adapter
        viewModel.food.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showProductDeletionDialog(product: Product) {
        val dialog = ProductDeletionDialogFragment(
            onDeleteClicked = {
                viewModel.removeFromList(product)
                val toast = Toast.makeText(context, "deleted an item: ${product.name} ${product.id} ", Toast.LENGTH_SHORT).show()
            },
            onDismissClicked = {
                parentFragmentManager.popBackStack()
                // val toast = Toast.makeText(context, "dismiss ", Toast.LENGTH_SHORT).show()
            }
        )
        dialog.show(requireActivity().supportFragmentManager, "tag")
    }
}

