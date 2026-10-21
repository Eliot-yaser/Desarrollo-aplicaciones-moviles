package com.example.mpazpro1.data.repository

import com.example.mpazpro1.data.local.Usuario
import com.example.mpazpro1.data.local.UsuarioDao
import com.example.mpazpro1.domain.Roles

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun login(username: String, clave: String): Usuario? {
        return usuarioDao.login(username.trim(), clave)
    }

    suspend fun existeUsername(username: String): Boolean {
        return usuarioDao.contarPorUsername(username.trim()) > 0
    }

    // Las cuentas creadas desde el formulario siempre son de estudiante
    suspend fun registrarEstudiante(nombre: String, username: String, clave: String) {
        usuarioDao.insertar(
            Usuario(
                nombre = nombre.trim(),
                username = username.trim(),
                clave = clave,
                rol = Roles.ESTUDIANTE
            )
        )
    }
}