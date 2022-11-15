package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.hfad.simplecaloriecalculator.CalcDatabase
import com.hfad.simplecaloriecalculator.productscreens.editproductscreen.EditProductFragment
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.productscreens.addproductscreen.AddProductFragment
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ProductsViewModel
    //private val viewModel: ProductsViewModel by activityViewModels()
    //val application = requireActivity()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = CalcDatabase.getInstance(application).productDao
        val viewModelFactory = ProductsViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory).get(ProductsViewModel::class.java)


        binding.fabGoToAddProductScreen.setOnClickListener {
            // var someProduct = Product(0, "Творог 5%", 0.17, 0.05, 0.018, 1.21, 100.0)
            //viewModel.clearBase()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, AddProductFragment::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("add_product_show_screen")
                .commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductItemAdapter({ product ->
            showProductDeletionDialog(product)
        }, {product ->
            //Toast.makeText(context, "item ${product.name} ${product.id} clicked", Toast.LENGTH_SHORT).show()
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
            }
        )
        dialog.show(requireActivity().supportFragmentManager, "tag")
    }
}

