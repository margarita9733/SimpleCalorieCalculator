package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.Product
import com.hfad.simplecaloriecalculator.databinding.ProductItemBinding


class ProductItemAdapter(
    val buttonListener: (product: Product) -> Unit,
    val itemLstnr: (product: Product) -> Unit
) :
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

        fun bind(
            item: Product,
            buttonListener: (product: Product) -> Unit,
            itemLstnr: (Product) -> Unit
        ) {
            binding.productName.text = item.name
            //binding.productProteins.text = getString(R.string.proteins_letter_placeholder, item.proteinsPerPortion.toString())
            binding.productProteins.text = "Б: " + item.proteinsPerPortion.format() + " г"
            binding.productFats.text = "Ж: " + item.fatsPerPortion.format() + " г"
            binding.productCarbs.text = "У: " + item.carbsPerPortion.format() + " г"
            binding.productPortionCalories.text = "ккал: " + item.caloriesPerPortion.format()
            binding.productPortionWeight.text = "Порция: " + item.portionWeight.format() + " г"
            binding.productOptionsButton.setOnClickListener { buttonListener(item) }
            binding.root.setOnClickListener /*{ Toast.makeText(binding.root.context, "tapped ${item.name}", Toast.LENGTH_SHORT).show() } */ { itemLstnr(item) }
        }

        fun Double.format() = "%.2f".format(this)

        class ProductDiffItemCallback : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem == newItem)

        }
    }
}