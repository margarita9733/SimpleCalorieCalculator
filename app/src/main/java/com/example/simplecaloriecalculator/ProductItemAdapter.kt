package com.example.simplecaloriecalculator

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.cardview.widget.CardView
import com.hfad.simplecaloriecalculator.R
import java.math.RoundingMode
import java.text.DecimalFormat


class ProductItemAdapter : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    var data = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProductItemViewHolder = ProductItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ProductItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        val productName = rootView.findViewById<TextView>(R.id.product_name)
        val productProteins = rootView.findViewById<TextView>(R.id.product_proteins)
        val productFats = rootView.findViewById<TextView>(R.id.product_fats)
        val productCarbs = rootView.findViewById<TextView>(R.id.product_carbs)
        val productCalories = rootView.findViewById<TextView>(R.id.product_portion_calories)
        val productPortionWeight = rootView.findViewById<TextView>(R.id.product_portion_weight)


        companion object {
            fun inflateFrom(parent: ViewGroup): ProductItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.product_item, parent, false) as CardView
                return ProductItemViewHolder(view)
            }
        }

        fun bind(item: Product) {

            productName.text = item.name
            productProteins.text = "Б: " + (roundOffDecimal(item.proteinsPerPortion)).toString() + " г"
            productFats.text = "Ж: " + (roundOffDecimal(item.fatsPerPortion)).toString() + " г"
            productCarbs.text = "У: " + (roundOffDecimal(item.carbsPerPortion)).toString() + " г"
            productCalories.text = "ккал: " + (roundOffDecimal(item.caloriesPerPortion)).toString()
            productPortionWeight.text = "Порция: " + item.portionWeight.toString() + " г"

        }

        private fun roundOffDecimal(number: Double): Double {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.FLOOR
            return df.format(number).toDouble()
        }

    }
}