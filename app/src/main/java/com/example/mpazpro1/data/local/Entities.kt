package com.example.mpazpro1.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios", indices = [Index(value = ["username"], unique = true)])
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,      // nombre ficticio
    val username: String,    // usuario ficticio
    val clave: String,       // clave ficticia (solo para el MVP)
    val rol: String          // "ESTUDIANTE", "DOCENTE" o "UTP"
)

@Entity(tableName = "asignaturas")
data class Asignatura(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String
)

@Entity(
    tableName = "objetivos_aprendizaje",
    foreignKeys = [ForeignKey(
        entity = Asignatura::class,
        parentColumns = ["id"],
        childColumns = ["asignaturaId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("asignaturaId")]
)
data class ObjetivoAprendizaje(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val asignaturaId: Int,
    val codigo: String,       // código oficial del OA
    val descripcion: String,
    val estado: String = "BORRADOR" // BORRADOR, EN_REVISION, AUTORIZADO, PUBLICADO, DESHABILITADO
)

// Estados para los recursos enviados por docentes.
object EstadoRecurso {
    const val PENDIENTE = "PENDIENTE"
    const val APROBADO = "APROBADO"
    const val RECHAZADO = "RECHAZADO"
}

@Entity(
    tableName = "recursos",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["autorId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Asignatura::class,
            parentColumns = ["id"],
            childColumns = ["asignaturaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("autorId"),
        Index("asignaturaId")
    ]
)
data class Recurso(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,
    val descripcion: String,
    val asignaturaId: Int,

    // Cuenta que creó el recurso.
    val autorId: Int,

    val estado: String = EstadoRecurso.PENDIENTE,

    // Comentario que escribe UTP al revisar.
    val observacionUtp: String = "",

    val fechaCreacion: Long = System.currentTimeMillis()
)