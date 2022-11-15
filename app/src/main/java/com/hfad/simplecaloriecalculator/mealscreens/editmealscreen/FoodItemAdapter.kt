package com.hfad.simplecaloriecalculator.mealscreens.editmealscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hfad.simplecaloriecalculator.databinding.FoodItemBinding
import com.hfad.simplecaloriecalculator.mealscreens.FoodToDisplay

class FoodItemAdapter : ListAdapter<FoodToDisplay, FoodItemAdapter.FoodItemViewHolder>(FoodItemViewHolder.FoodDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : FoodItemViewHolder = FoodItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FoodItemViewHolder(val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): FoodItemAdapter.FoodItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodItemBinding.inflate(layoutInflater, parent, false)
                return FoodItemAdapter.FoodItemViewHolder(binding)
            }
        }

        fun bind(item: FoodToDisplay) {

            binding.foodName.text = item.foodItem.name
            binding.foodCalories.text = item.getCaloriesPerPortion().format() + " ккал"
            binding.foodWeight.text = "Вес: " + item.portionEntered.format() + " г"
            binding.foodProteins.text = "Б: " + item.getProteinsPerPortion().format() + " г"
            binding.foodFats.text = "У: " + item.getFatsPerPortion().format() + " г"
            binding.foodCarbs.text = "Ж: " + item.getCarbsPerPortion().format() + " г"

        }

        fun Double.format() = "%.2f".format(this)

        class FoodDiffItemCallback : DiffUtil.ItemCallback<FoodToDisplay>() {

            override fun areItemsTheSame(oldItem: FoodToDisplay, newItem: FoodToDisplay): Boolean = ((oldItem.id == newItem.id) && (oldItem::class == newItem::class))

            override fun areContentsTheSame(oldItem: FoodToDisplay, newItem: FoodToDisplay): Boolean = (oldItem == newItem)

        }
    }
}
