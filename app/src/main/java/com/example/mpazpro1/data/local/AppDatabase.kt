package com.example.mpazpro1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(
    entities = [
        Usuario::class,
        Asignatura::class,
        ObjetivoAprendizaje::class,
        Recurso::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun asignaturaDao(): AsignaturaDao
    abstract fun objetivoDao(): ObjetivoDao

    abstract fun recursoDao(): RecursoDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS `recursos` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `titulo` TEXT NOT NULL,
                `descripcion` TEXT NOT NULL,
                `asignaturaId` INTEGER NOT NULL,
                `autorId` INTEGER NOT NULL,
                `estado` TEXT NOT NULL,
                `observacionUtp` TEXT NOT NULL,
                `fechaCreacion` INTEGER NOT NULL,
                FOREIGN KEY (`autorId`)
                    REFERENCES `usuarios` (`id`)
                    ON UPDATE NO ACTION ON DELETE CASCADE,
                FOREIGN KEY (`asignaturaId`)
                    REFERENCES `asignaturas` (`id`)
                    ON UPDATE NO ACTION ON DELETE CASCADE
            )
            """.trimIndent()
                )

                db.execSQL(
                    """
            CREATE INDEX IF NOT EXISTS `index_recursos_autorId`
            ON `recursos` (`autorId`)
            """.trimIndent()
                )

                db.execSQL(
                    """
            CREATE INDEX IF NOT EXISTS `index_recursos_asignaturaId`
            ON `recursos` (`asignaturaId`)
            """.trimIndent()
                )
            }
        }

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mpaz_pro.db"
                ).addMigrations(MIGRATION_1_2)
                    .addCallback(object : Callback() {
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