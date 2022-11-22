package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.hfad.simplecaloriecalculator.databinding.FragmentDishesBinding
import androidx.lifecycle.ViewModelProvider
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.dishscreens.adddishscreen.AddDishFragment
import com.hfad.simplecaloriecalculator.dishscreens.editdishscreen.EditDishFragment
import com.hfad.simplecaloriecalculator.productscreens.editproductscreen.EditProductFragment
import com.hfad.simplecaloriecalculator.productscreens.productsscreen.ProductDeletionDialogFragment


class DishesFragment : Fragment() {

    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!

    // private var viewModel: DishesViewModel = ViewModelProvider(this).get(DishesViewModel::class.java)
    private val viewModel: DishesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fabGoToAddDishScreen.setOnClickListener {

            val d = Dish(giveId())
            viewModel.addDish(d)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, AddDishFragment(d), null)
                .setReorderingAllowed(true)
                .addToBackStack("add_dish_show_screen")
                .commit()
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DishItemAdapter({ dish ->
            showDishDeletionDialog(dish)
        }, { dish ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditDishFragment(dish), null)
                .setReorderingAllowed(true)
                .addToBackStack("edit_dish_show_screen")
                .commit()
        })

        binding.dishesList.adapter = adapter
        viewModel.dishes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDishDeletionDialog(dish: Dish) {
        val dialog = DishDeletionDialogFragment(
            onDeleteClicked = {
                viewModel.removeDish(dish)
                val toast = Toast.makeText(context, "deleted an item: ${dish.name} ", Toast.LENGTH_SHORT).show()
            },
            onDismissClicked = {
                parentFragmentManager.popBackStack()
            }
        )
        dialog.show(requireActivity().supportFragmentManager, "tag")
    }

    fun giveId(): Long = (viewModel.lastDishId() + 1).toLong()
}