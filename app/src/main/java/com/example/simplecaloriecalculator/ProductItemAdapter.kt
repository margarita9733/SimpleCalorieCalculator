package com.example.simplecaloriecalculator

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.simplecaloriecalculator.databinding.ProductItemBinding
import java.math.RoundingMode
import java.text.DecimalFormat


class ProductItemAdapter(val buttonListener: (product: Product) -> Unit) : ListAdapter<Product, ProductItemAdapter.ProductItemViewHolder>(ProductDiffItemCallback()) {


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
            binding.productProteins.text = "Б: " + (roundOffDecimal(item.proteinsPerPortion)).toString() + " г"
            binding.productFats.text = "Ж: " + (roundOffDecimal(item.fatsPerPortion)).toString() + " г"
            binding.productCarbs.text = "У: " + (roundOffDecimal(item.carbsPerPortion)).toString() + " г"
            binding.productPortionCalories.text = "ккал: " + (roundOffDecimal(item.caloriesPerPortion)).toString()
            binding.productPortionWeight.text = "Порция: " + item.portionWeight.toString() + " г"
            binding.productOptionsButton.setOnClickListener {
                buttonListener(item)
            }
        }

        private fun roundOffDecimal(number: Double): Double {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.FLOOR
            return df.format(number).toDouble()
        }

    }
}

class ProductDiffItemCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product ): Boolean
            = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = (oldItem == newItem)


}