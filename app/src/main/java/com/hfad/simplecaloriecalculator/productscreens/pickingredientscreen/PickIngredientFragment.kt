package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.R

class PickIngredientFragment(dishToUpdate: Dish, val addOrEdit: Boolean) : Fragment() {

/* private var _binding: FragmentDishesBinding? = null
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
        val viewModelFactory = ProductsViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(PickIngredientViewModel::class.java)


     //   binding.fabGoToAddProductScreen.setOnClickListener {

       //     parentFragmentManager.beginTransaction()
        //        .replace(R.id.fragment_container_view, AddProductFragment::class.java, null)
        //        .setReorderingAllowed(true)
        //        .addToBackStack("add_product_show_screen")
       //         .commit()
      //  }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductItemAdapter({ product ->
            showProductDeletionDialog(product)
        }, { product ->
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
*/
}