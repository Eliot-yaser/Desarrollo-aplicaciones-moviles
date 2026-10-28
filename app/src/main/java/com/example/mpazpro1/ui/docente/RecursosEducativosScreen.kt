package com.example.mpazpro1.ui.docente

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Recursos ficticios para esta entrega
private data class RecursoEducativo(
    val asignatura: String,
    val unidad: String,
    val objetivo: String,
    val titulo: String,
    val tipo: String,
    val descripcion: String
)

@Composable
fun RecursosEducativosScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    var asignaturaSeleccionada by remember {
        mutableStateOf<String?>(null)
    }

    var recursoSeleccionado by remember {
        mutableStateOf<RecursoEducativo?>(null)
    }

    val asignaturas = listOf(
        "Lenguaje y Comunicación",
        "Matemática",
        "Ciencias Naturales",
        "Historia, Geografía y Cs. Sociales"
    )

    val recursos = listOf(
        RecursoEducativo(
            asignatura = "Lenguaje y Comunicación",
            unidad = "Unidad 1",
            objetivo = "OA de ejemplo 1",
            titulo = "Reconocimiento de vocales",
            tipo = "Guía de aprendizaje",
            descripcion = "Identificar las vocales A, E, I, O, U mediante ejemplos y actividades visuales."
        ),
        RecursoEducativo(
            asignatura = "Matemática",
            unidad = "Unidad 1",
            objetivo = "OA de ejemplo 2",
            titulo = "Números del 1 al 10",
            tipo = "Actividad visual",
            descripcion = "Reconocer números y contar objetos utilizando imágenes."
        ),
        RecursoEducativo(
            asignatura = "Ciencias Naturales",
            unidad = "Unidad 1",
            objetivo = "OA de ejemplo 3",
            titulo = "Los seres vivos",
            tipo = "Material de apoyo",
            descripcion = "Conocer las características básicas de los seres vivos."
        ),
        RecursoEducativo(
            asignatura = "Historia, Geografía y Cs. Sociales",
            unidad = "Unidad 1",
            objetivo = "OA de ejemplo 4",
            titulo = "Mi familia y mi comunidad",
            tipo = "Guía visual",
            descripcion = "Reconocer integrantes de la familia y su importancia en la comunidad."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // Botón para regresar
        OutlinedButton(
            onClick = {
                when {
                    recursoSeleccionado != null -> {
                        recursoSeleccionado = null
                    }
                    asignaturaSeleccionada != null -> {
                        asignaturaSeleccionada = null
                    }
                    else -> onVolver()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver")
        }

        Text(
            text = if (recursoSeleccionado != null) {
                "Detalle del recurso"
            } else {
                asignaturaSeleccionada ?: "Recursos educativos"
            },
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Material didáctico de 1° Básico",
            style = MaterialTheme.typography.bodyMedium
        )

        // Pantalla principal de asignaturas
        if (asignaturaSeleccionada == null) {

            Text(
                text = "Selecciona una asignatura",
                style = MaterialTheme.typography.titleMedium
            )

            asignaturas.forEach { asignatura ->

                Card(
                    onClick = {
                        asignaturaSeleccionada = asignatura
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = asignatura,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("Consultar unidades y recursos")

                        Text(
                            text = "Ver recursos →",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Listado de recursos de la asignatura
        else if (recursoSeleccionado == null) {

            Text(
                text = "Recursos disponibles",
                style = MaterialTheme.typography.titleMedium
            )

            recursos
                .filter {
                    it.asignatura == asignaturaSeleccionada
                }
                .forEach { recurso ->

                    Card(
                        onClick = {
                            recursoSeleccionado = recurso
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = recurso.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(recurso.unidad)
                            Text(recurso.objetivo)
                            Text("Tipo: ${recurso.tipo}")

                            Text(
                                text = "Ver detalles →",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
        }

        // Detalle del recurso seleccionado
        else {

            recursoSeleccionado?.let { recurso ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = recurso.titulo,
                            style = MaterialTheme.typography.titleLarge
                        )

                        HorizontalDivider()

                        Text("Asignatura: ${recurso.asignatura}")
                        Text("Unidad: ${recurso.unidad}")
                        Text("Objetivo: ${recurso.objetivo}")
                        Text("Tipo: ${recurso.tipo}")

                        Text(
                            text = "Descripción del material",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(recurso.descripcion)
                    }
                }

                Text(
                    text = "Este es un recurso de demostración.",
                    style = MaterialTheme.typography.bodySmall
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
                    Text("Utilizar recurso")
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
                    Text("Escuchar explicación")
                }
            }
        }
    }
}
