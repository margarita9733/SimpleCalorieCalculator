package com.hfad.simplecaloriecalculator.productscreens.pickproductfordishscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.databinding.IngredientItemBinding
import com.hfad.simplecaloriecalculator.dishscreens.Ingredient

class IngredientItemAdapter(
    val buttonListener: (ingredient: Ingredient) -> Unit,
    val itemLstnr: (ingredient: Ingredient) -> Unit
) : ListAdapter<Ingredient, IngredientItemAdapter.IngredientItemViewHolder>(IngredientItemViewHolder.IngredientDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : IngredientItemViewHolder = IngredientItemAdapter.IngredientItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: IngredientItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, buttonListener, itemLstnr)
    }

    class IngredientItemViewHolder(val binding: IngredientItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): IngredientItemAdapter.IngredientItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(layoutInflater, parent, false)
                return IngredientItemViewHolder(binding)
            }
        }

        fun bind(
            item: Ingredient,
            buttonListener: (ingredient: Ingredient) -> Unit,
            itemLstnr: (Ingredient) -> Unit
        ) {
            binding.ingredientName.text = item.name
            binding.ingredientWeight.text = "Вес: " + item.portionEntered.format() + " г"
            binding.ingredientProteins.text = "Б: " + item.getProteinsPerPortion().format() + " г"
            binding.ingredientFats.text = "Б: " + item.getFatsPerPortion().format() + " г"
            binding.ingredientCarbs.text = "Б: " + item.getCarbsPerPortion().format() + " г"
            binding.ingredientCalories.text = "Б: " + item.getCaloriesPerPortion().format() + " г"
            binding.deleteIngredientButton.setOnClickListener { buttonListener(item) }
            binding.root.setOnClickListener { itemLstnr(item) }
        }

        fun Double.format() = "%.2f".format(this)
        class IngredientDiffItemCallback : DiffUtil.ItemCallback<Ingredient>() {

            override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean = (oldItem == newItem)

        }
    }
}