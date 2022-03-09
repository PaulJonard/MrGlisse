package com.example.mrglisse.repository

import androidx.lifecycle.LiveData
import com.example.mrglisse.data.AlpinDAO
import com.example.mrglisse.model.Alpin

class AlpinRepository(
    private val alpinDAO: AlpinDAO) {

    //Alpin
    val selectAllAlpins: LiveData<List<Alpin>> = alpinDAO.selectAllAlpins()

    suspend fun selectAlpin(alpin: Alpin){
        alpinDAO.selectAlpin(alpin.id)
    }

    suspend fun addAlpin(alpin: Alpin){
        alpinDAO.addAlpin(alpin)
    }

    suspend fun updateAlpin(alpin: Alpin){
        alpinDAO.updateAlpin(alpin)
    }

    suspend fun deleteAlpin(alpin: Alpin){
        alpinDAO.deleteAlpin(alpin)
    }
}