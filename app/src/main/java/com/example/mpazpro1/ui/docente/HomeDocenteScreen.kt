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
    onCerrarSesion: () -> Unit,
    onAsignarObjetivos: () -> Unit,
    onMisCursos: () -> Unit,
    onInformeRendimiento: () -> Unit,
    onRecursosEducativos: () -> Unit
) {
    PanelRolScreen(
        saludo = "¡Hola, $nombre!",
        subtitulo = "Panel docente · Escuela Escritora Marcela Paz",
        opciones = listOf(
            OpcionPanel(
                titulo = "Mis cursos",
                descripcion = "Gestiona tus cursos y asignaturas",
                icono = Icons.Filled.Groups,
                onClick = onMisCursos
            ),
            OpcionPanel(
                titulo = "Informes de rendimiento",
                descripcion = "Revisa el progreso de tus estudiantes",
                icono = Icons.Filled.Assessment,
                onClick = onInformeRendimiento
            ),
            OpcionPanel(
                titulo = "Asignar objetivos",
                descripcion = "Configura los OA por asignatura",
                icono = Icons.Filled.Checklist,
                onClick = onAsignarObjetivos
            ),

            OpcionPanel(
                titulo = "Recursos",
                descripcion = "Material de apoyo y guías",
                icono = Icons.Filled.Folder,
                onClick = onRecursosEducativos
            )
        ),
        onCerrarSesion = onCerrarSesion
    )
}