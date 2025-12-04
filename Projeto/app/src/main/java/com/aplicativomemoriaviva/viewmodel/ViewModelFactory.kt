package com.aplicativomemoriaviva.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aplicativomemoriaviva.data.repository.AppRepository

class ViewModelFactory(
    private val app: Application,
    private val repo: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(app, repo) as T

            modelClass.isAssignableFrom(IdosoViewModel::class.java) ->
                IdosoViewModel(app, repo) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
