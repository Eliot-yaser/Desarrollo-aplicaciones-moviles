package com.example.mpazpro1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios WHERE username = :username AND clave = :clave LIMIT 1")
    suspend fun login(username: String, clave: String): Usuario?

    @Query("SELECT COUNT(*) FROM usuarios WHERE username = :username")
    suspend fun contarPorUsername(username: String): Int

    @Insert
    suspend fun insertar(usuario: Usuario)
}

@Dao
interface AsignaturaDao {
    @Query("SELECT * FROM asignaturas")
    fun todas(): Flow<List<Asignatura>>
}

@Dao
interface ObjetivoDao {
    @Query("SELECT * FROM objetivos_aprendizaje WHERE asignaturaId = :asignaturaId")
    fun porAsignatura(asignaturaId: Int): Flow<List<ObjetivoAprendizaje>>

    @Insert
    suspend fun insertar(objetivo: ObjetivoAprendizaje)
}