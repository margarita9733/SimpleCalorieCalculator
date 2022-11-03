package com.hfad.simplecaloriecalculator.dayscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.MealItemBinding


class MealItemAdapter : ListAdapter<Dish, MealItemAdapter.MealItemViewHolder>(MealItemViewHolder.MealDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MealItemViewHolder = MealItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MealItemViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): MealItemAdapter.MealItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MealItemBinding.inflate(layoutInflater, parent, false)
                return MealItemAdapter.MealItemViewHolder(binding)
            }
        }

        fun bind(item: Dish) {
            binding.mealName.text = item.name
            binding.mealPortionCalories.text = item.caloriesPerPortion.format() + " ккал"
            binding.mealPortionWeight.text = "Вес: " + item.portionWeight.format() + " г"
        }

        fun Double.format() = "%.2f".format(this)

        class MealDiffItemCallback : DiffUtil.ItemCallback<Dish>() {

            override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean = (oldItem == newItem)

        }
    }

}