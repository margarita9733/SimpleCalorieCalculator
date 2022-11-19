package com.hfad.simplecaloriecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.simplecaloriecalculator.databinding.ActivityMainBinding
import com.hfad.simplecaloriecalculator.dayscreen.DayFragment
import com.hfad.simplecaloriecalculator.dishscreens.dishesscreen.DishesFragment
import com.hfad.simplecaloriecalculator.productscreens.productsscreen.ProductsFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.diary_navigation -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, DayFragment::class.java, null)
                        .setReorderingAllowed(true)
                        .commit()
                    true
                }
                R.id.products_navigation -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, ProductsFragment::class.java, null)
                        .setReorderingAllowed(true)
                        .commit()
                    true
                }
                R.id.dishes_navigation -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, DishesFragment::class.java, null)
                        .setReorderingAllowed(true)
                        .commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.selectedItemId = R.id.diary_navigation
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}