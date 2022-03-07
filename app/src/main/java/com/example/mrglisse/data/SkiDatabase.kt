package com.example.mrglisse.data

import androidx.room.Database
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond

@Database(entities = [Alpin::class, Fond::class], version = 1)
abstract class SkiDatabase {
}