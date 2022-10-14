package com.hfad.simplecaloriecalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*  В классе БЖУК на 1 грамм продукта, вес порции в граммах.
    В UI БЖУК на 100 грамм.
    При отображении в UI значение * 100, при получении из UI  значение / 100.
 */
@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0,

    @ColumnInfo(name = "product_name")
    var name: String = "",

    @ColumnInfo(name = "product_proteins")
    var proteins: Double = 0.0,

    @ColumnInfo(name = "product_fats")
    var fats: Double = 0.0,

    @ColumnInfo(name = "product_carbs")
    var carbs: Double = 0.0,

    @ColumnInfo(name = "product_calories")
    var calories: Double = 0.0,

    @ColumnInfo(name = "product_portion_weight")
    var portionWeight: Double = 0.0
) : Food {

    val proteinsPerPortion
    get() = proteins * portionWeight

    val fatsPerPortion
    get() = fats * portionWeight

    val carbsPerPortion
    get() = carbs * portionWeight

    val caloriesPerPortion
    get() = calories * portionWeight

    override fun getProteinsPer100(proteinsPerGram: Double): Double = proteins * 100
    override fun getFatsPer100(fatsPerGram: Double): Double = fats * 100
    override fun getCarbsPer100(carbsPerGram: Double): Double = carbs * 100
    override fun getCaloriesPer100(caloriesPerGram: Double): Double = calories * 100
}
