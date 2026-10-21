package com.example.mpazpro1.ui.navigation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mpazpro1.data.local.AppDatabase
import com.example.mpazpro1.data.repository.UsuarioRepository
import com.example.mpazpro1.ui.login.LoginViewModel
import com.example.mpazpro1.ui.registro.RegistroViewModel

// Crea los ViewModel que reciben el repositorio como parámetro
object AppViewModelProvider {
    fun factory(context: Context): ViewModelProvider.Factory {
        val repository = UsuarioRepository(AppDatabase.get(context.applicationContext).usuarioDao())
        return viewModelFactory {
            initializer { LoginViewModel(repository) }
            initializer { RegistroViewModel(repository) }
        }
    }
}