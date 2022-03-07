package com.example.mrglisse.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mrglisse.model.Alpin

@Dao
interface AlpinDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlpin(alpin: Alpin)

    @Query("SELECT * FROM alpin_table ORDER BY id ASC")
    suspend fun selectAllAlpins(): LiveData<List<Alpin>>

    @Query("SELECT * FROM alpin_table WHERE id = :id")
    suspend fun selectAlpin(id: Int)

    @Update
    suspend fun  updateAlpin(alpin: Alpin)

    @Delete
    suspend fun deleteAlpin(alpin: Alpin)
}