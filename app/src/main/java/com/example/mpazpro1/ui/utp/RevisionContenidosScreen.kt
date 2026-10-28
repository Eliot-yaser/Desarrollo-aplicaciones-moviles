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

// Contenidos ficticios para la evaluación
private data class ContenidoRevision(
    val titulo: String,
    val asignatura: String,
    val unidad: String,
    val objetivo: String,
    val tipo: String,
    val descripcion: String,
    val estado: String
)

@Composable
fun RevisionContenidosScreen(
    onVolver: () -> Unit,
    onCrearContenido: () -> Unit,
    onCrearActividad: () -> Unit,
    onCrearPruebaFinal: () -> Unit
) {
    val context = LocalContext.current

    val contenidos = remember {
        listOf(
            ContenidoRevision(
                titulo = "Reconocimiento de vocales",
                asignatura = "Lenguaje y Comunicación",
                unidad = "Unidad 1",
                objetivo = "OA de ejemplo 1",
                tipo = "Actividad educativa",
                descripcion = "Identificar las vocales A, E, I, O, U mediante imágenes y ejemplos.",
                estado = "En revisión"
            ),
            ContenidoRevision(
                titulo = "Números del 1 al 10",
                asignatura = "Matemática",
                unidad = "Unidad 1",
                objetivo = "OA de ejemplo 2",
                tipo = "Guía de aprendizaje",
                descripcion = "Reconocer y contar números del 1 al 10 utilizando objetos.",
                estado = "Borrador"
            ),
            ContenidoRevision(
                titulo = "Los seres vivos",
                asignatura = "Ciencias Naturales",
                unidad = "Unidad 1",
                objetivo = "OA de ejemplo 3",
                tipo = "Material visual",
                descripcion = "Reconocer las características principales de los seres vivos.",
                estado = "En revisión"
            ),
            ContenidoRevision(
                titulo = "Mi familia y comunidad",
                asignatura = "Historia, Geografía y Cs. Sociales",
                unidad = "Unidad 1",
                objetivo = "OA de ejemplo 4",
                tipo = "Guía educativa",
                descripcion = "Identificar integrantes de la familia y su importancia.",
                estado = "Aprobado (ejemplo)"
            )
        )
    }

    var contenidoSeleccionado by remember {
        mutableStateOf<ContenidoRevision?>(null)
    }

    var mostrarHistorial by remember {
        mutableStateOf(false)
    }

    var observacion by remember {
        mutableStateOf("")
    }

    // Mensaje para las funciones futuras
    fun mostrarMensaje() {
        Toast.makeText(
            context,
            "Para una futura actualización",
            Toast.LENGTH_SHORT
        ).show()
    }

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
                    mostrarHistorial -> {
                        mostrarHistorial = false
                    }

                    contenidoSeleccionado != null -> {
                        contenidoSeleccionado = null
                    }

                    else -> onVolver()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (
                    contenidoSeleccionado == null &&
                    !mostrarHistorial
                ) {
                    "← Volver al panel UTP"
                } else {
                    "← Volver a contenidos"
                }
            )
        }

        Text(
            text = when {
                mostrarHistorial -> "Historial de revisiones"
                contenidoSeleccionado != null -> "Detalle del contenido"
                else -> "Revisión de contenidos"
            },
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Gestión pedagógica · 1° Básico",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        // LISTADO DE CONTENIDOS
        if (
            contenidoSeleccionado == null &&
            !mostrarHistorial
        ) {

            Text(
                text = "Contenidos disponibles",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Selecciona un material para revisar su información."
            )

            contenidos.forEach { contenido ->

                Card(
                    onClick = {
                        contenidoSeleccionado = contenido
                        observacion = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = contenido.titulo,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(contenido.asignatura)

                        Text(contenido.unidad)

                        Text("Estado: ${contenido.estado}")

                        Text(
                            text = "Revisar contenido →",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Button(
                onClick = onCrearContenido,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear contenido educativo")
            }

            Button(
                onClick = onCrearActividad,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear actividad o desafío")
            }

            Button(
                onClick = onCrearPruebaFinal,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear prueba final")
            }

            OutlinedButton(
                onClick = {
                    mostrarHistorial = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver historial de revisiones")
            }
        }

        // HISTORIAL DE EJEMPLO
        else if (mostrarHistorial) {

            Text(
                text = "Modificaciones y aprobaciones",
                style = MaterialTheme.typography.titleMedium
            )

            val historial = listOf(
                "25/10/2026 - Vocales - En revisión",
                "24/10/2026 - Números - Borrador",
                "23/10/2026 - Seres vivos - En revisión",
                "22/10/2026 - Familia - Aprobado"
            )

            historial.forEach { registro ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(registro)
                        Text(
                            text = "Responsable: UTP de ejemplo",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Text(
                text = "Historial ficticio para demostración.",
                style = MaterialTheme.typography.bodySmall
            )
        }

        // DETALLE Y REVISIÓN
        else {

            contenidoSeleccionado?.let { contenido ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Text(
                            text = contenido.titulo,
                            style = MaterialTheme.typography.titleLarge
                        )

                        HorizontalDivider()

                        Text(
                            "Asignatura: ${contenido.asignatura}"
                        )

                        Text("Unidad: ${contenido.unidad}")

                        Text("Objetivo: ${contenido.objetivo}")

                        Text("Tipo: ${contenido.tipo}")

                        Text("Estado: ${contenido.estado}")

                        HorizontalDivider()

                        Text(
                            text = "Descripción del contenido",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(contenido.descripcion)
                    }
                }

                Text(
                    text = "Observación pedagógica",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = observacion,
                    onValueChange = {
                        observacion = it
                    },
                    label = {
                        Text("Observación de UTP")
                    },
                    placeholder = {
                        Text("Escribe una observación de ejemplo")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                Text(
                    text = "Acciones de revisión",
                    style = MaterialTheme.typography.titleMedium
                )

                Button(
                    onClick = {
                        mostrarMensaje()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Aprobar contenido")
                }

                OutlinedButton(
                    onClick = {
                        mostrarMensaje()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Rechazar contenido")
                }

                Button(
                    onClick = {
                        mostrarMensaje()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Publicar contenido")
                }

                OutlinedButton(
                    onClick = {
                        mostrarMensaje()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Deshabilitar contenido")
                }

                Text(
                    text = "Estas acciones son demostrativas y no modifican datos.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
