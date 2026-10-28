package com.example.mpazpro1.ui.utp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import com.example.mpazpro1.ui.componentes.OpcionPanel
import com.example.mpazpro1.ui.componentes.PanelRolScreen

@Composable
fun HomeUtpScreen(
    nombre: String,
    onCerrarSesion: () -> Unit,
    onConfiguracionCurricular: () -> Unit,
    onRevisionContenidos: () -> Unit,
    onReportesGenerales: () -> Unit
) {
    PanelRolScreen(
        saludo = "¡Hola, $nombre!",
        subtitulo = "Unidad Técnico Pedagógica",
        opciones = listOf(
            OpcionPanel(
                titulo = "Revisión de contenidos",
                descripcion = "Evalúa y autoriza materiales",
                icono = Icons.Filled.FactCheck,
                onClick = onRevisionContenidos
            ),
            OpcionPanel(
                titulo = "Reportes generales",
                descripcion = "Visualiza el rendimiento por curso",
                icono = Icons.Filled.Assessment,
                onClick = onReportesGenerales
            ),
            OpcionPanel("Gestión de docentes", "Administra usuarios y asignaturas", Icons.Filled.Groups),
            OpcionPanel(
                titulo = "Configuración curricular",
                descripcion = "Alinea con el plan de estudios",
                icono = Icons.Filled.Settings,
                onClick = onConfiguracionCurricular
            )
        ),
        onCerrarSesion = onCerrarSesion
    )
}