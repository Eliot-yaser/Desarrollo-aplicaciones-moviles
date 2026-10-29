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

@Dao
interface RecursoDao {

    // Recursos aprobados disponibles para todos los docentes.
    @Query("""
        SELECT * FROM recursos
        WHERE estado = 'APROBADO'
        ORDER BY fechaCreacion DESC, id DESC
    """)
    fun publicados(): Flow<List<Recurso>>

    // Cada docente podrá consultar sus propios envíos.
    @Query("""
        SELECT * FROM recursos
        WHERE autorId = :autorId
        ORDER BY fechaCreacion DESC, id DESC
    """)
    fun porAutor(autorId: Int): Flow<List<Recurso>>

    // Recursos que UTP debe revisar.
    @Query("""
        SELECT * FROM recursos
        WHERE estado = 'PENDIENTE'
        ORDER BY fechaCreacion ASC, id ASC
    """)
    fun pendientes(): Flow<List<Recurso>>

    @Insert
    suspend fun insertar(recurso: Recurso): Long

    // Solo permite resolver recursos que siguen pendientes.
    @Query("""
        UPDATE recursos
        SET estado = :nuevoEstado,
            observacionUtp = :observacion
        WHERE id = :recursoId
          AND estado = 'PENDIENTE'
    """)
    suspend fun resolverRevision(
        recursoId: Int,
        nuevoEstado: String,
        observacion: String
    ): Int

    @Query("""
    SELECT id, nombre
    FROM usuarios
    WHERE id IN (SELECT autorId FROM recursos)
""")
    fun autores(): Flow<List<AutorRecurso>>
}
data class AutorRecurso(
    val id: Int,
    val nombre: String
)