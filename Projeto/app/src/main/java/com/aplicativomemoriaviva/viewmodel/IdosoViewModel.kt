package com.aplicativomemoriaviva.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aplicativomemoriaviva.data.entities.Idoso
import com.aplicativomemoriaviva.data.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IdosoViewModel(
    application: Application,
    private val repo: AppRepository
) : AndroidViewModel(application) {

    val idosos: StateFlow<List<Idoso>> = repo.idososFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun adicionar(idoso: Idoso, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repo.insertIdoso(idoso)
            onComplete()
        }
    }

    fun atualizar(idoso: Idoso, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repo.updateIdoso(idoso)
            onComplete()
        }
    }

    fun deletar(idoso: Idoso, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repo.deleteIdoso(idoso)
            onComplete()
        }
    }

    suspend fun getById(id: Int): Idoso? = repo.getIdosoById(id)
}
