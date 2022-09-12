package com.hfad.simplecaloriecalculator

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.databinding.ProductItemBinding
import java.lang.NumberFormatException
import java.math.RoundingMode
import java.text.DecimalFormat


class ProductItemAdapter(val buttonListener: (product: Product) -> Unit) :
    ListAdapter<Product, ProductItemAdapter.ProductItemViewHolder>(ProductItemViewHolder.ProductDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProductItemViewHolder = ProductItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, buttonListener)
    }

    class ProductItemViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ProductItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
                return ProductItemViewHolder(binding)
            }
        }

        fun bind(item: Product, buttonListener: (product: Product) -> Unit) {
            binding.productName.text = item.name
            binding.productProteins.text = "Б: " + item.proteinsPerPortion.format(2) + " г"
            binding.productFats.text = "Ж: " + item.fatsPerPortion.format(2) + " г"
            binding.productCarbs.text = "У: " + item.carbsPerPortion.format(2) + " г"
            binding.productPortionCalories.text = "ккал: " + item.caloriesPerPortion.format(2)
            binding.productPortionWeight.text = "Порция: " + item.portionWeight.format(2) + " г"
            binding.productOptionsButton.setOnClickListener {
                buttonListener(item)
            }
        }

        fun Double.format(scale: Int) = "%.${scale}f".format(this)



        /*fun bind(item: Product, buttonListener: (product: Product) -> Unit) {
            binding.productName.text = item.name
            binding.productProteins.text = "Б: " + fm(item.proteinsPerPortion) + " г"
            binding.productFats.text = "Ж: " + fm(item.fatsPerPortion) + " г"
            binding.productCarbs.text = "У: " + fm(item.carbsPerPortion) + " г"
            binding.productPortionCalories.text = "ккал: " + fm(item.caloriesPerPortion)
            binding.productPortionWeight.text = "Порция: " + item.portionWeight + " г"
            binding.productOptionsButton.setOnClickListener {
                buttonListener(item)
            }
        }

        fun fm(number: Double): String {
            return String.format("%.2f", number)
        }*/



        class ProductDiffItemCallback : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem == newItem)

        }
    }
}