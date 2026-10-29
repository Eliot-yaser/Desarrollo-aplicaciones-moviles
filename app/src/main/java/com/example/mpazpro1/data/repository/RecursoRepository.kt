package com.example.mpazpro1.data.repository

import com.example.mpazpro1.data.local.EstadoRecurso
import com.example.mpazpro1.data.local.Recurso
import com.example.mpazpro1.data.local.RecursoDao
import com.example.mpazpro1.data.local.Usuario
import com.example.mpazpro1.domain.Roles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecursoRepository(
    private val recursoDao: RecursoDao
) {

    fun publicados(): Flow<List<Recurso>> {
        return recursoDao.publicados()
    }

    fun misRecursos(usuario: Usuario): Flow<List<Recurso>> {
        return recursoDao.porAutor(usuario.id)
    }

    fun pendientes(usuario: Usuario): Flow<List<Recurso>> {
        require(usuario.rol == Roles.UTP) {
            "Solo UTP puede consultar las revisiones pendientes"
        }

        return recursoDao.pendientes()
    }

    fun autores(): Flow<Map<Int, String>> {
        return recursoDao.autores().map { usuarios ->
            usuarios.associate { it.id to it.nombre }
        }
    }

    suspend fun crear(
        usuario: Usuario,
        titulo: String,
        descripcion: String,
        asignaturaId: Int
    ) {
        require(
            usuario.rol == Roles.DOCENTE ||
                    usuario.rol == Roles.UTP
        ) {
            "Solo Docente y UTP pueden crear recursos"
        }

        require(titulo.isNotBlank()) {
            "Escribe el título del recurso"
        }

        require(descripcion.isNotBlank()) {
            "Escribe la descripción del recurso"
        }

        require(asignaturaId in 1..4) {
            "Selecciona una asignatura válida"
        }

        val estadoInicial = if (usuario.rol == Roles.UTP) {
            EstadoRecurso.APROBADO
        } else {
            EstadoRecurso.PENDIENTE
        }

        recursoDao.insertar(
            Recurso(
                titulo = titulo.trim(),
                descripcion = descripcion.trim(),
                asignaturaId = asignaturaId,
                autorId = usuario.id,
                estado = estadoInicial
            )
        )
    }

    suspend fun revisar(
        usuario: Usuario,
        recursoId: Int,
        aprobar: Boolean,
        observacion: String
    ) {
        require(usuario.rol == Roles.UTP) {
            "Solo UTP puede aprobar o rechazar recursos"
        }

        require(aprobar || observacion.isNotBlank()) {
            "Escribe el motivo del rechazo"
        }

        val nuevoEstado = if (aprobar) {
            EstadoRecurso.APROBADO
        } else {
            EstadoRecurso.RECHAZADO
        }

        val actualizados = recursoDao.resolverRevision(
            recursoId = recursoId,
            nuevoEstado = nuevoEstado,
            observacion = observacion.trim()
        )

        check(actualizados == 1) {
            "El recurso ya fue revisado o no está disponible"
        }
    }
}