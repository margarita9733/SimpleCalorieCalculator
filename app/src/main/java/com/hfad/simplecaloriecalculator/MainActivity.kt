package com.hfad.simplecaloriecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hfad.simplecaloriecalculator.R
import com.hfad.simplecaloriecalculator.databinding.ActivityMainBinding
import com.hfad.simplecaloriecalculator.dayscreen.DayFragment
import com.hfad.simplecaloriecalculator.productscreens.productsscreen.FoodFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding  get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.diary_navigation ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, DayFragment::class.java, null)
                    .setReorderingAllowed(true)
                    .commit()
                    true
                }
                R.id.products_navigation -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, FoodFragment::class.java, null)
                        .setReorderingAllowed(true)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}