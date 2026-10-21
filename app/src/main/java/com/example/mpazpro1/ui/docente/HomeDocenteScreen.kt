package com.example.mpazpro1.ui.docente

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.runtime.Composable
import com.example.mpazpro1.ui.componentes.OpcionPanel
import com.example.mpazpro1.ui.componentes.PanelRolScreen

@Composable
fun HomeDocenteScreen(
    nombre: String,
    onCerrarSesion: () -> Unit
) {
    PanelRolScreen(
        saludo = "¡Hola, $nombre!",
        subtitulo = "Panel docente · Escuela Escritora Marcela Paz",
        opciones = listOf(
            OpcionPanel("Mis cursos", "Gestiona tus cursos y asignaturas", Icons.Filled.Groups),
            OpcionPanel("Informes de rendimiento", "Revisa el progreso de tus estudiantes", Icons.Filled.Assessment),
            OpcionPanel("Asignar objetivos", "Configura los OA por asignatura", Icons.Filled.Checklist),
            OpcionPanel("Recursos", "Material de apoyo y guías", Icons.Filled.Folder)
        ),
        onCerrarSesion = onCerrarSesion
    )
}