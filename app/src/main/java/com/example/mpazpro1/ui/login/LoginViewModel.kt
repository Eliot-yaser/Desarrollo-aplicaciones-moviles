package com.example.mpazpro1.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpazpro1.data.local.Usuario
import com.example.mpazpro1.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _usuarioLogueado = MutableStateFlow<Usuario?>(null)
    val usuarioLogueado: StateFlow<Usuario?> = _usuarioLogueado.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun login(username: String, clave: String) {
        if (username.isBlank() || clave.isBlank()) {
            _error.value = "Escribe tu usuario y tu contraseña"
            return
        }
        viewModelScope.launch {
            _error.value = null
            val usuario = repository.login(username, clave)
            if (usuario != null) {
                _usuarioLogueado.value = usuario
            } else {
                _error.value = "Usuario o contraseña incorrectos"
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }

    fun cerrarSesion() {
        _usuarioLogueado.value = null
        _error.value = null
    }
}