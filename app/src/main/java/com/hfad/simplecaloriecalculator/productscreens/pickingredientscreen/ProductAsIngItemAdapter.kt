package com.hfad.simplecaloriecalculator.productscreens.pickingredientscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.ProductAsIngredientItemBinding


class ProductAsIngItemAdapter(
    val itemLstnr: (product: Product) -> Unit
) : ListAdapter<Product, ProductAsIngItemAdapter.ProductAsIngItemViewHolder>(ProductAsIngItemViewHolder.ProductAsIngDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProductAsIngItemViewHolder = ProductAsIngItemAdapter.ProductAsIngItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ProductAsIngItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemLstnr)
    }

    class ProductAsIngItemViewHolder(val binding: ProductAsIngredientItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ProductAsIngItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductAsIngredientItemBinding.inflate(layoutInflater, parent, false)
                return ProductAsIngItemViewHolder(binding)
            }
        }

        fun bind(
            item: Product,
            itemLstnr: (Product) -> Unit
        ) {
            binding.productName.text = item.name
            binding.productDefaultWeight.text = "Вес: " + item.portionWeight.format() + " г"
            binding.productProteins.text = "Б: " + item.proteinsPerPortion.format() + " г"
            binding.productFats.text = "Ж: " + item.fatsPerPortion.format() + " г"
            binding.productCarbs.text = "У: " + item.carbsPerPortion.format() + " г"
            binding.productPortionCalories.text = "ккал: " + item.caloriesPerPortion.format() + " г"
            binding.root.setOnClickListener { itemLstnr(item) }
        }

        fun Double.format() = "%.2f".format(this)
        class ProductAsIngDiffItemCallback : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem == newItem)

        }
    }
}