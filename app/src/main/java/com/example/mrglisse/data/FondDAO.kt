package com.example.mrglisse.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond

@Dao
interface FondDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFond(fond: Fond)

    @Query("SELECT * FROM fond_table ORDER BY id ASC")
    fun selectAllFonds(): LiveData<List<Fond>>

    @Query("SELECT * FROM fond_table WHERE id = :id")
    fun selectFond(id: Int):LiveData<Fond>

    @Update
    suspend fun  updateFond(fond: Fond)

    @Delete
    suspend fun deleteFond(fond: Fond)
}