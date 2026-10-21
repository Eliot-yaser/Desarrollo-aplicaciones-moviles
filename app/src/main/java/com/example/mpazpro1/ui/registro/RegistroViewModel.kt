package com.example.mpazpro1.ui.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpazpro1.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistroUiState(
    val nombre: String = "",
    val username: String = "",
    val clave: String = "",
    val confirmar: String = "",
    val errorNombre: String? = null,
    val errorUsername: String? = null,
    val errorClave: String? = null,
    val errorConfirmar: String? = null,
    val guardando: Boolean = false,
    val exito: Boolean = false
)

class RegistroViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _estado = MutableStateFlow(RegistroUiState())
    val estado: StateFlow<RegistroUiState> = _estado.asStateFlow()

    fun onNombre(valor: String) = _estado.update { it.copy(nombre = valor, errorNombre = null) }
    fun onUsername(valor: String) = _estado.update { it.copy(username = valor, errorUsername = null) }
    fun onClave(valor: String) = _estado.update { it.copy(clave = valor, errorClave = null) }
    fun onConfirmar(valor: String) = _estado.update { it.copy(confirmar = valor, errorConfirmar = null) }

    fun registrar() {
        val s = _estado.value

        val errorNombre = if (s.nombre.trim().length < 3) "Escribe un nombre de al menos 3 letras" else null
        val errorUsername = when {
            s.username.length < 4 -> "Mínimo 4 caracteres"
            !s.username.all { it.isLetterOrDigit() } -> "Solo letras y números, sin espacios"
            else -> null
        }
        val errorClave = if (s.clave.length < 4) "Mínimo 4 caracteres" else null
        val errorConfirmar = if (s.confirmar != s.clave) "Las contraseñas no coinciden" else null

        if (errorNombre != null || errorUsername != null || errorClave != null || errorConfirmar != null) {
            _estado.update {
                it.copy(
                    errorNombre = errorNombre,
                    errorUsername = errorUsername,
                    errorClave = errorClave,
                    errorConfirmar = errorConfirmar
                )
            }
            return
        }

        viewModelScope.launch {
            _estado.update { it.copy(guardando = true) }
            if (repository.existeUsername(s.username)) {
                _estado.update { it.copy(guardando = false, errorUsername = "Ese usuario ya existe") }
            } else {
                repository.registrarEstudiante(s.nombre, s.username, s.clave)
                _estado.update { it.copy(guardando = false, exito = true) }
            }
        }
    }
}