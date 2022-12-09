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
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.database.CalcDatabase
import com.hfad.simplecaloriecalculator.dishscreens.adddishscreen.AddDishFragment
import com.hfad.simplecaloriecalculator.dishscreens.editdishscreen.EditDishFragment


class DishesFragment : Fragment() {

    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: DishesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dbInstance = CalcDatabase.getInstance(application)
        val productDao = dbInstance.productDao
        val dishDao = dbInstance.dishDao
        val dishProductDao = dbInstance.dishProductDao
        val viewModelFactory = DishesViewModelFactory(dishDao, dishProductDao, productDao)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DishesViewModel::class.java)

        viewModel.triggerNavigationToEditDish.observe(viewLifecycleOwner, Observer {
            it?.let {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, EditDishFragment(it, true), null)
                    .setReorderingAllowed(true)
                    .addToBackStack("edit_dish_show_screen")
                    .commit()
                viewModel.onNavigationEnded()
            }
        })

        binding.fabGoToAddDishScreen.setOnClickListener {
            viewModel.addDish(Dish())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DishItemAdapter({ dish ->
            showDishDeletionDialog(dish)
        }, { dish ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, EditDishFragment(dish.id, false), null)
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
                val toast = Toast.makeText(context, "deleted an item: ${dish.name}, id ${dish.id} ", Toast.LENGTH_SHORT).show()
            },
            onDismissClicked = {
                parentFragmentManager.popBackStack()
            }
        )
        dialog.show(requireActivity().supportFragmentManager, "tag")
    }


}