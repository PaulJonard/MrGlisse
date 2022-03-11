package com.example.mrglisse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mrglisse.data.SkiDatabase
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.repository.AlpinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlpinViewModel(application: Application): AndroidViewModel(application) {

    val readAllAlpins: LiveData<List<Alpin>>
    private val repository : AlpinRepository

    init {
        val alpinDao = SkiDatabase.getDatabase(
            application
        ).alpinDao()
        repository = AlpinRepository(alpinDao)
        readAllAlpins = repository.selectAllAlpins
    }

    fun addAlpin(alpin: Alpin){
        viewModelScope.launch(Dispatchers.IO){
            repository.addAlpin(alpin)
        }
    }

    fun updateAlpin(alpin: Alpin){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAlpin(alpin)
        }
    }

    fun deleteAlpin(alpin: Alpin){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAlpin(alpin)
        }
    }
}