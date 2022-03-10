package com.example.mrglisse.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mrglisse.model.Alpin

@Dao
interface AlpinDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlpin(alpin: Alpin)

    @Query("SELECT * FROM alpin_table ORDER BY id ASC")
    fun selectAllAlpins(): LiveData<List<Alpin>>

    @Query("SELECT * FROM alpin_table WHERE id = :id")
    fun selectAlpin(id: Int): LiveData<Alpin>

    @Update
    suspend fun  updateAlpin(alpin: Alpin)


    @Delete
    suspend fun deleteAlpin(alpin: Alpin)
}