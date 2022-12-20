package com.hfad.simplecaloriecalculator.dishscreens

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.databinding.IngredientItemBinding

class IngredientItemAdapter(
    val buttonListener: (ingredient: Ingredient) -> Unit,
    val itemListener: (ingredient: Ingredient) -> Unit
) :
    ListAdapter<Ingredient, IngredientItemAdapter.IngredientItemViewHolder>(com.hfad.simplecaloriecalculator.dishscreens.IngredientItemAdapter.IngredientItemViewHolder.IngredientDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int)
            : IngredientItemViewHolder = IngredientItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: IngredientItemAdapter.IngredientItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemListener, buttonListener)
    }

    class IngredientItemViewHolder(val binding: IngredientItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): IngredientItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(layoutInflater, parent, false)
                return IngredientItemViewHolder(binding)
            }
        }

        fun bind(item: Ingredient, itemListener: (ingredient: Ingredient) -> Unit, buttonListener: (ingredient: Ingredient) -> Unit) {
            binding.ingredientName.text = item.name
            binding.ingredientWeight.text = item.portionEntered.format() + "г"
            binding.ingredientCalories.text = "ккал: " + item.getCaloriesPerPortion().format()
            binding.deleteIngredientButton.setOnClickListener { buttonListener(item) }
            binding.root.setOnClickListener { itemListener(item) }

        }

        fun Double.format() = "%.2f".format(this)
        class IngredientDiffItemCallback : DiffUtil.ItemCallback<Ingredient>() {
            override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean = (oldItem == newItem)
        }
    }
}