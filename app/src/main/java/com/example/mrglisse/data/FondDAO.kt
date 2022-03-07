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
    suspend fun selectAllFonds(): LiveData<List<Fond>>

    @Query("SELECT * FROM fond_table WHERE id = :id")
    suspend fun selectFond(id: Int)

    @Update
    suspend fun  updateFond(fond: Fond)

    @Delete
    suspend fun deleteFond(fond: Fond)
}