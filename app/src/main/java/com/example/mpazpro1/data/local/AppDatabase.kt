package com.example.mpazpro1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Usuario::class, Asignatura::class, ObjetivoAprendizaje::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun asignaturaDao(): AsignaturaDao
    abstract fun objetivoDao(): ObjetivoDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mpaz_pro.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Usuarios ficticios
                        db.execSQL("INSERT INTO usuarios (nombre, username, clave, rol) VALUES ('Estudiante Prueba', 'estudiante1', '1234', 'ESTUDIANTE')")
                        db.execSQL("INSERT INTO usuarios (nombre, username, clave, rol) VALUES ('Docente Prueba', 'docente1', '1234', 'DOCENTE')")
                        db.execSQL("INSERT INTO usuarios (nombre, username, clave, rol) VALUES ('UTP Prueba', 'utp1', '1234', 'UTP')")
                        // Asignaturas
                        db.execSQL("INSERT INTO asignaturas (nombre) VALUES ('Lenguaje y Comunicación')")
                        db.execSQL("INSERT INTO asignaturas (nombre) VALUES ('Matemática')")
                        db.execSQL("INSERT INTO asignaturas (nombre) VALUES ('Ciencias Naturales')")
                        db.execSQL("INSERT INTO asignaturas (nombre) VALUES ('Historia, Geografía y Ciencias Sociales')")
                    }
                }).build().also { INSTANCE = it }
            }
    }
}