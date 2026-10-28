package com.example.mpazpro1.ui.docente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Datos ficticios para mostrar el funcionamiento
private data class EstudianteDemo(
    val nombre: String,
    val avance: Int
)

@Composable
fun MisCursosScreen(
    onVolver: () -> Unit
) {
    val estudiantesA = listOf(
        EstudianteDemo("Estudiante 01", 75),
        EstudianteDemo("Estudiante 02", 60),
        EstudianteDemo("Estudiante 03", 85)
    )

    val estudiantesB = listOf(
        EstudianteDemo("Estudiante 04", 55),
        EstudianteDemo("Estudiante 05", 90)
    )

    var cursoSeleccionado by remember {
        mutableStateOf("1° Básico A")
    }

    var estudianteSeleccionado by remember {
        mutableStateOf<EstudianteDemo?>(null)
    }

    val estudiantes = if (cursoSeleccionado == "1° Básico A") {
        estudiantesA
    } else {
        estudiantesB
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {


        OutlinedButton(
            onClick = {
                if (estudianteSeleccionado != null) {
                    estudianteSeleccionado = null
                } else {
                    onVolver()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (estudianteSeleccionado != null) {
                    "← Volver a estudiantes"
                } else {
                    "← Volver al panel docente"
                }
            )
        }


        Text(
            text = "Mis cursos",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Escuela Escritora Marcela Paz",
            style = MaterialTheme.typography.bodyMedium
        )

        if (estudianteSeleccionado == null) {

            Text(
                text = "Selecciona un curso",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("1° Básico A", "1° Básico B").forEach { curso ->
                    FilterChip(
                        selected = cursoSeleccionado == curso,
                        onClick = {
                            cursoSeleccionado = curso
                        },
                        label = { Text(curso) }
                    )
                }
            }

            Text(
                text = "Estudiantes de $cursoSeleccionado",
                style = MaterialTheme.typography.titleMedium
            )

            estudiantes.forEach { estudiante ->

                Card(
                    onClick = {
                        estudianteSeleccionado = estudiante
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = estudiante.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("Avance general: ${estudiante.avance}%")

                        LinearProgressIndicator(
                            progress = { estudiante.avance / 100f },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "Toca para ver detalles",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

        } else {

            estudianteSeleccionado?.let { estudiante ->

                Text(
                    text = estudiante.nombre,
                    style = MaterialTheme.typography.titleLarge
                )

                Text("Curso: $cursoSeleccionado")

                Text(
                    text = "Avance por asignatura (datos de ejemplo)",
                    style = MaterialTheme.typography.titleMedium
                )

                val asignaturas = listOf(
                    "Lenguaje y Comunicación" to estudiante.avance,
                    "Matemática" to 65,
                    "Ciencias Naturales" to 80,
                    "Historia y Ciencias Sociales" to 70
                )

                asignaturas.forEach { (nombre, avance) ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(nombre)

                            Text("Progreso: $avance%")

                            LinearProgressIndicator(
                                progress = { avance / 100f },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        estudianteSeleccionado = null
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver a estudiantes")
                }
            }
        }
    }
}
