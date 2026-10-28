package com.example.mpazpro1.ui.utp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Datos ficticios para mostrar los reportes
private data class CursoReporteUtp(
    val nombre: String,
    val estudiantes: Int,
    val lenguaje: Int,
    val matematica: Int,
    val ciencias: Int,
    val historia: Int
) {
    val promedio: Int
        get() = (lenguaje + matematica + ciencias + historia) / 4
}

@Composable
fun ReportesGeneralesScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    val cursos = remember {
        listOf(
            CursoReporteUtp(
                nombre = "1.º Básico A",
                estudiantes = 30,
                lenguaje = 86,
                matematica = 79,
                ciencias = 83,
                historia = 72
            ),
            CursoReporteUtp(
                nombre = "1.º Básico B",
                estudiantes = 30,
                lenguaje = 78,
                matematica = 71,
                ciencias = 75,
                historia = 64
            )
        )
    }

    var cursoSeleccionado by remember {
        mutableStateOf("Todos los cursos")
    }

    val cursosFiltrados = if (
        cursoSeleccionado == "Todos los cursos"
    ) {
        cursos
    } else {
        cursos.filter { it.nombre == cursoSeleccionado }
    }

    val totalEstudiantes = cursosFiltrados.sumOf {
        it.estudiantes
    }

    val progresoGeneral = cursosFiltrados.sumOf {
        it.promedio * it.estudiantes
    } / totalEstudiantes

    // Promedio por asignatura, según el curso seleccionado
    val lenguaje = cursosFiltrados.sumOf {
        it.lenguaje * it.estudiantes
    } / totalEstudiantes

    val matematica = cursosFiltrados.sumOf {
        it.matematica * it.estudiantes
    } / totalEstudiantes

    val ciencias = cursosFiltrados.sumOf {
        it.ciencias * it.estudiantes
    } / totalEstudiantes

    val historia = cursosFiltrados.sumOf {
        it.historia * it.estudiantes
    } / totalEstudiantes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver al panel UTP")
        }

        Text(
            text = "Reportes generales",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Resumen del rendimiento académico de 1.º Básico.",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Datos ficticios para demostración.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider()

        // SECCIÓN 1: FILTRO DE CURSO
        Text(
            text = "Seleccionar curso",
            style = MaterialTheme.typography.titleMedium
        )

        SelectorCursoReporteUtp(
            seleccionado = cursoSeleccionado,
            opciones = listOf(
                "Todos los cursos",
                "1.º Básico A",
                "1.º Básico B"
            ),
            onSeleccionar = {
                cursoSeleccionado = it
            }
        )

        HorizontalDivider()

        // SECCIÓN 2: RESUMEN GENERAL
        Text(
            text = "Resumen académico",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Estudiantes: $totalEstudiantes",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Cursos incluidos: ${cursosFiltrados.size}"
                )

                Text(
                    text = "Progreso general: $progresoGeneral%",
                    style = MaterialTheme.typography.titleMedium
                )

                LinearProgressIndicator(
                    progress = {
                        progresoGeneral / 100f
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        HorizontalDivider()

        // SECCIÓN 3: RENDIMIENTO POR ASIGNATURA
        Text(
            text = "Rendimiento por asignatura",
            style = MaterialTheme.typography.titleMedium
        )

        ProgresoAsignaturaUtp(
            nombre = "Lenguaje y Comunicación",
            porcentaje = lenguaje
        )

        ProgresoAsignaturaUtp(
            nombre = "Matemática",
            porcentaje = matematica
        )

        ProgresoAsignaturaUtp(
            nombre = "Ciencias Naturales",
            porcentaje = ciencias
        )

        ProgresoAsignaturaUtp(
            nombre = "Historia, Geografía y Cs. Sociales",
            porcentaje = historia
        )

        HorizontalDivider()

        // SECCIÓN 4: OBJETIVOS DE APRENDIZAJE
        Text(
            text = "Objetivos de aprendizaje",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Indicadores de OA de ejemplo.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        ProgresoAsignaturaUtp(
            nombre = "OA de ejemplo 1: Reconocer vocales",
            porcentaje = lenguaje
        )

        ProgresoAsignaturaUtp(
            nombre = "OA de ejemplo 2: Resolver sumas simples",
            porcentaje = matematica
        )

        ProgresoAsignaturaUtp(
            nombre = "OA de ejemplo 3: Identificar seres vivos",
            porcentaje = ciencias
        )

        HorizontalDivider()

        // SECCIÓN 5: RESUMEN POR CURSO
        Text(
            text = "Resumen por curso",
            style = MaterialTheme.typography.titleMedium
        )

        cursosFiltrados.forEach { curso ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = curso.nombre,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Estudiantes: ${curso.estudiantes}"
                    )

                    Text(
                        text = "Progreso académico: ${curso.promedio}%"
                    )

                    LinearProgressIndicator(
                        progress = {
                            curso.promedio / 100f
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        HorizontalDivider()

        // SECCIÓN 6: OBSERVACIONES
        Text(
            text = "Observaciones pedagógicas",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Fortalezas observadas",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "Se observa un buen avance en " +
                            "Lenguaje y Ciencias Naturales."
                )

                Text(
                    text = "Aspectos por reforzar",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "Se recomienda reforzar actividades " +
                            "de Matemática e Historia."
                )
            }
        }

        HorizontalDivider()

        // SECCIÓN 7: ACCIONES DEMOSTRATIVAS
        Text(
            text = "Acciones del reporte",
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Para una futura actualización",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Generar reporte general")
        }

        OutlinedButton(
            onClick = {
                Toast.makeText(
                    context,
                    "Para una futura actualización",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Descargar reporte PDF")
        }

        Text(
            text = "Los reportes son demostrativos. " +
                    "No se generan archivos ni se consultan " +
                    "datos reales de estudiantes.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}

// Componente para mostrar progreso por asignatura u OA
@Composable
private fun ProgresoAsignaturaUtp(
    nombre: String,
    porcentaje: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$nombre: $porcentaje%",
            style = MaterialTheme.typography.bodyMedium
        )

        LinearProgressIndicator(
            progress = {
                porcentaje / 100f
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// Selector de curso
@Composable
private fun SelectorCursoReporteUtp(
    seleccionado: String,
    opciones: List<String>,
    onSeleccionar: (String) -> Unit
) {
    var expandido by remember {
        mutableStateOf(false)
    }

    Box {
        OutlinedButton(
            onClick = {
                expandido = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(seleccionado)
        }

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = {
                expandido = false
            }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(opcion)
                    },
                    onClick = {
                        onSeleccionar(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}
