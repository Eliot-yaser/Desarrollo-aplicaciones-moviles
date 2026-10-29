package com.example.mpazpro1.ui.navigation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mpazpro1.data.local.AppDatabase
import com.example.mpazpro1.data.local.Usuario
import com.example.mpazpro1.data.repository.RecursoRepository
import com.example.mpazpro1.data.repository.UsuarioRepository
import com.example.mpazpro1.ui.login.LoginViewModel
import com.example.mpazpro1.ui.recursos.RecursosViewModel
import com.example.mpazpro1.ui.registro.RegistroViewModel

object AppViewModelProvider {

    // Se conserva la creación de Login y Registro.
    fun factory(context: Context): ViewModelProvider.Factory {
        val database = AppDatabase.get(context.applicationContext)

        val repository = UsuarioRepository(
            database.usuarioDao()
        )

        return viewModelFactory {
            initializer {
                LoginViewModel(repository)
            }

            initializer {
                RegistroViewModel(repository)
            }
        }
    }

    // Crea el ViewModel de recursos para la cuenta actual.
    fun factoryRecursos(
        context: Context,
        usuario: Usuario
    ): ViewModelProvider.Factory {
        val database = AppDatabase.get(context.applicationContext)

        val repository = RecursoRepository(
            database.recursoDao()
        )

        return viewModelFactory {
            initializer {
                RecursosViewModel(
                    repository = repository,
                    usuario = usuario
                )
            }
        }
    }
}