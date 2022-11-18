package com.hfad.simplecaloriecalculator.dishscreens.dishesscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.Dish
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.DishItemBinding


class DishItemAdapter(
    val buttonListener: (dish: Dish) -> Unit,
    val itemLstnr: (dish: Dish) -> Unit
) :
    ListAdapter<Dish, DishItemAdapter.DishItemViewHolder>(DishItemViewHolder.DishDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : DishItemViewHolder = DishItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: DishItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, buttonListener, itemLstnr)
    }

    class DishItemViewHolder(val binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root) {


        companion object {
            fun inflateFrom(parent: ViewGroup): DishItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DishItemBinding.inflate(layoutInflater, parent, false)
                return DishItemViewHolder(binding)
            }
        }

        fun bind(item: Dish, buttonListener: (dish: Dish) -> Unit, itemLstnr: (dish: Dish) -> Unit) {
            binding.dishName.text = item.name
            binding.dishProteins.text = "Б: " + item.proteinsPerDefaultPortion.format() + " г"
            binding.dishFats.text = "Ж: " + item.fatsPerDefaultPortion.format() + " г"
            binding.dishCarbs.text = "У: " + item.carbsPerDefaultPortion.format() + " г"
            binding.dishPortionCalories.text = "ккал: " + item.caloriesPerDefaultPortion.format() + " г"
            binding.dishPortionWeight.text = "Порция: " + item.defaultPortionWeight.format() + " г"
            binding.dishOptionsButton.setOnClickListener { buttonListener(item) }
            binding.root.setOnClickListener {itemLstnr(item)}
        }

        fun Double.format() = "%.2f".format(this)

        class DishDiffItemCallback : DiffUtil.ItemCallback<Dish>() {
            override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean = (oldItem == newItem)
        }
    }
}