package com.aplicativomemoriaviva.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aplicativomemoriaviva.data.entities.Usuario
import com.aplicativomemoriaviva.data.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val repo: AppRepository
) : AndroidViewModel(application) {

    val usuarios: StateFlow<List<Usuario>> = repo.usuariosFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun cadastrarUsuario(usuario: Usuario, onComplete: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            repo.insertUsuario(usuario)
            onComplete(true)
        }
    }

    fun login(email: String, senha: String, onResult: (Boolean, Usuario?) -> Unit) {
        viewModelScope.launch {
            val user = repo.login(email, senha)
            onResult(user != null, user)
        }
    }
}
