package com.hfad.simplecaloriecalculator.productsscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.ProductItemBinding


class ProductItemAdapter(val buttonListener: (product: Product) -> Unit, val itemLstnr: (product: Product) -> Unit) :
    ListAdapter<Product, ProductItemAdapter.ProductItemViewHolder>(ProductItemViewHolder.ProductDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProductItemViewHolder = ProductItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, buttonListener, itemLstnr)
    }

    class ProductItemViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ProductItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
                return ProductItemViewHolder(binding)
            }
        }

        fun bind(item: Product, buttonListener: (product: Product) -> Unit, itemLstnr: (Product) -> Unit) {
            binding.productName.text = item.name
            binding.productProteins.text = "Б: " + item.proteinsPerPortion.format(2) + " г"
            binding.productFats.text = "Ж: " + item.fatsPerPortion.format(2) + " г"
            binding.productCarbs.text = "У: " + item.carbsPerPortion.format(2) + " г"
            binding.productPortionCalories.text = "ккал: " + item.caloriesPerPortion.format(2)
            binding.productPortionWeight.text = "Порция: " + item.portionWeight.format(2) + " г"
            binding.productOptionsButton.setOnClickListener { buttonListener(item) }
            var c = binding.root.context
            binding.root.setOnClickListener /*{ Toast.makeText(c, "clicked ${item.name}", Toast.LENGTH_SHORT).show() } */ { itemLstnr(item) }
        }

        fun Double.format(scale: Int) = "%.${scale}f".format(this)

        class ProductDiffItemCallback : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem.productId == newItem.productId)

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem == newItem)

        }
    }
}