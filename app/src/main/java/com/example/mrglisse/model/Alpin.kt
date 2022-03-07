package com.example.mrglisse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alpin_table")
data class Alpin(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String,
    val model: String,
    val price: Double,
    val size: Int
): Ski(brand, model, price, size
) {
    override fun showOverView(): String {
        TODO("Not yet implemented")
    }
}