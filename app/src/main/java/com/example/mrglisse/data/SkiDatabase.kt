package com.example.mrglisse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond

@Database(entities = [Alpin::class, Fond::class], version = 1, exportSchema = false)
abstract class SkiDatabase : RoomDatabase() {

    abstract fun alpinDao(): AlpinDAO
    abstract fun fondDao(): FondDAO

    companion object {
        @Volatile
        private var INSTANCE: SkiDatabase? = null

        fun getDatabase(context: Context): SkiDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SkiDatabase::class.java,
                    "ski_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}