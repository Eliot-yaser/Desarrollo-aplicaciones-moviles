package com.example.mpazpro1.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Public
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mpazpro1.ui.theme.SubjectCiencias
import com.example.mpazpro1.ui.theme.SubjectHistoria
import com.example.mpazpro1.ui.theme.SubjectLenguaje
import com.example.mpazpro1.ui.theme.SubjectMatematica

// Los id coinciden con el orden en que se insertan las asignaturas en AppDatabase (1 a 4)
data class AsignaturaUi(
    val id: Int,
    val nombre: String,
    val color: Color,
    val icono: ImageVector
)

val asignaturasUi = listOf(
    AsignaturaUi(1, "Lenguaje y Comunicación", SubjectLenguaje, Icons.AutoMirrored.Filled.MenuBook),
    AsignaturaUi(2, "Matemática", SubjectMatematica, Icons.Filled.Calculate),
    AsignaturaUi(3, "Ciencias Naturales", SubjectCiencias, Icons.Filled.Eco),
    AsignaturaUi(4, "Historia, Geografía y Cs. Sociales", SubjectHistoria, Icons.Filled.Public)
)