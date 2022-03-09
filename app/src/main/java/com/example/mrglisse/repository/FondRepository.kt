package com.example.mrglisse.repository

import androidx.lifecycle.LiveData
import com.example.mrglisse.data.FondDAO
import com.example.mrglisse.model.Fond

class FondRepository(
    private val fondDAO: FondDAO
) {
    //Fond
    val selectAllFonds: LiveData<List<Fond>> = fondDAO.selectAllFonds()

    suspend fun selectFond(fond: Fond){
        fondDAO.selectFond(fond.id)
    }

    suspend fun addFond(fond: Fond){
        fondDAO.addFond(fond)
    }

    suspend fun updateFond(fond: Fond){
        fondDAO.updateFond(fond)
    }

    suspend fun deleteFond(fond: Fond){
        fondDAO.deleteFond(fond)
    }
}