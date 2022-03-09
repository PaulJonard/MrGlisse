package com.example.mrglisse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mrglisse.data.SkiDatabase
import com.example.mrglisse.model.Fond
import com.example.mrglisse.repository.FondRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FondViewModel(application: Application): AndroidViewModel(application) {

    val readAllFonds: LiveData<List<Fond>>
    private val repository : FondRepository

    init {
        val fondDao = SkiDatabase.getDatabase(
            application
        ).fondDao()
        repository = FondRepository(fondDao)
        readAllFonds = repository.selectAllFonds
    }

    fun addFond(fond: Fond){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFond(fond)
        }
    }

    fun updateFond(fond: Fond){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFond(fond)
        }
    }

    fun deleteFond(fond: Fond){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFond(fond)
        }
    }
}