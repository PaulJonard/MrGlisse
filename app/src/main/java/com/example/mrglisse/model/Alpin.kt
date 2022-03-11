package com.example.mrglisse.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "alpin_table")
data class Alpin(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    override val brand: String,
    override val model: String,
    override val price: Double,
    override val size: Int
): Ski(brand, model, price, size
), Parcelable {
    override fun showOverView(): String {
        return "not yet implemented"
    }
}