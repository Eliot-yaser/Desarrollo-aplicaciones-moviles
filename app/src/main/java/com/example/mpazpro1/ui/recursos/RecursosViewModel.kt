package com.example.mpazpro1.ui.recursos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpazpro1.data.local.Recurso
import com.example.mpazpro1.data.local.Usuario
import com.example.mpazpro1.data.repository.RecursoRepository
import com.example.mpazpro1.domain.Roles
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecursosViewModel(
    private val repository: RecursoRepository,
    val usuario: Usuario
) : ViewModel() {

    val esUtp: Boolean
        get() = usuario.rol == Roles.UTP

    // Recursos disponibles para todos los docentes.
    val publicados = repository.publicados().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val autores = repository.autores().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyMap()
    )

    // Recursos creados por la cuenta actual.
    val misRecursos = repository.misRecursos(usuario).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    // La lista de revisión solo está disponible para UTP.
    val pendientes = (
            if (esUtp) {
                repository.pendientes(usuario)
            } else {
                flowOf(emptyList<Recurso>())
            }
            ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _procesando = MutableStateFlow(false)
    val procesando = _procesando.asStateFlow()

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje = _mensaje.asStateFlow()

    fun limpiarMensaje() {
        _mensaje.value = null
    }

    fun crearRecurso(
        titulo: String,
        descripcion: String,
        asignaturaId: Int,
        onCreado: () -> Unit
    ) {
        ejecutar {
            repository.crear(
                usuario = usuario,
                titulo = titulo,
                descripcion = descripcion,
                asignaturaId = asignaturaId
            )

            _mensaje.value = if (esUtp) {
                "Recurso publicado"
            } else {
                "Recurso enviado a revisión"
            }

            onCreado()
        }
    }

    fun revisarRecurso(
        recursoId: Int,
        aprobar: Boolean,
        observacion: String,
        onRevisado: () -> Unit
    ) {
        ejecutar {
            repository.revisar(
                usuario = usuario,
                recursoId = recursoId,
                aprobar = aprobar,
                observacion = observacion
            )

            _mensaje.value = if (aprobar) {
                "Recurso aprobado y publicado"
            } else {
                "Recurso rechazado"
            }

            onRevisado()
        }
    }

    private fun ejecutar(operacion: suspend () -> Unit) {
        if (_procesando.value) return

        _procesando.value = true
        _mensaje.value = null

        viewModelScope.launch {
            try {
                operacion()
            } catch (e: CancellationException) {
                throw e
            } catch (e: IllegalArgumentException) {
                _mensaje.value = e.message ?: "Revisa los datos ingresados"
            } catch (e: IllegalStateException) {
                _mensaje.value = e.message ?: "No se pudo completar la operación"
            } catch (e: Exception) {
                _mensaje.value = "No se pudo completar la operación. Inténtalo nuevamente."
            } finally {
                _procesando.value = false
            }
        }
    }
}