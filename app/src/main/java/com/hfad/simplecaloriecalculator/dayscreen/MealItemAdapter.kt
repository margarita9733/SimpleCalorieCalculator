package com.hfad.simplecaloriecalculator.dayscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.MealItemBinding


class MealItemAdapter : ListAdapter<FoodToDisplay, MealItemAdapter.MealItemViewHolder>(MealItemViewHolder.MealDiffItemCallback()) {
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

        fun bind(item: FoodToDisplay) {
            //binding.mealTime.text =
            binding.mealCalories.text = item.getCaloriesPerPortion().format() + " ккал"
            binding.mealPortionWeight.text = "Вес: " + item.portionEntered.format() + " г"
        }

        fun Double.format() = "%.2f".format(this)


         /*
         в адаптер будет приходить список объектов-оберток FoodToDisplay,при этом :
         id продукта уникально в списке продуктов,
         id блюда уникально в списке блюд,
         а если в пп придут объект-блюдо и объект-продукт с одинаковыми id - ???*/
        class MealDiffItemCallback : DiffUtil.ItemCallback<FoodToDisplay>() {

            override fun areItemsTheSame(oldItem: FoodToDisplay, newItem:FoodToDisplay): Boolean = ((oldItem.id == newItem.id) && (oldItem::class == newItem::class))

            override fun areContentsTheSame(oldItem: FoodToDisplay, newItem: FoodToDisplay): Boolean = (oldItem == newItem)

        }
    }

}