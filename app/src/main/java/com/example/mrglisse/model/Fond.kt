package com.example.mrglisse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fond_table")
data class Fond(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    override val brand: String,
    override val model: String,
    override val price: Double,
    override val size: Int,
) : Ski(brand, model, price, size
) {
    override fun showOverView(): String {
        TODO("Not yet implemented")
    }
}