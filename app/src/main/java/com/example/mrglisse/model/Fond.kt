package com.example.mrglisse.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "fond_table")
data class Fond(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    override val brand: String,
    override val model: String,
    override val price: Double,
    override val size: Int,
) : Ski(brand, model, price, size
), Parcelable {
    override fun showOverView(): String {
        val toReturn = StringBuilder()
        toReturn.appendLine("Une paire de ski adaptée pour le plat!")
        toReturn.appendLine("Les skis " + this.model + " part leur longueur de " + this.size + "cm, vous fatigueront moins sur la distance!")
        return toReturn.toString()
    }
}