package com.example.mpazpro1.ui.estudiante

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Datos ficticios de los desafíos
private data class DesafioDemoEstudiante(
    val titulo: String,
    val descripcion: String,
    val instrucciones: String,
    val completado: Boolean
)

@Composable
fun DesafiosScreen(
    asignaturaId: Int,
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    val nombreAsignatura = when (asignaturaId) {
        1 -> "Lenguaje y Comunicación"
        2 -> "Matemática"
        3 -> "Ciencias Naturales"
        4 -> "Historia y Geografía"
        else -> "Asignatura"
    }

    // Desafíos diferentes para cada asignatura
    val desafios = when (asignaturaId) {

        1 -> listOf(
            DesafioDemoEstudiante(
                titulo = "Desafío 1: Encuentra las vocales",
                descripcion = "Reconocer las vocales.",
                instrucciones = "Observa las letras y encuentra " +
                        "las vocales A, E, I, O y U.",
                completado = true
            ),
            DesafioDemoEstudiante(
                titulo = "Desafío 2: Palabras con vocales",
                descripcion = "Identificar vocales en palabras.",
                instrucciones = "Lee palabras sencillas " +
                        "e identifica qué vocales contienen.",
                completado = false
            )
        )

        2 -> listOf(
            DesafioDemoEstudiante(
                titulo = "Desafío 1: Contar objetos",
                descripcion = "Reconocer cantidades del 1 al 10.",
                instrucciones = "Cuenta los objetos de una imagen " +
                        "y reconoce la cantidad correcta.",
                completado = true
            ),
            DesafioDemoEstudiante(
                titulo = "Desafío 2: Sumas sencillas",
                descripcion = "Resolver sumas pequeñas.",
                instrucciones = "Imagina que tienes dos manzanas " +
                        "y recibes tres más. ¿Cuántas tienes?",
                completado = false
            )
        )

        3 -> listOf(
            DesafioDemoEstudiante(
                titulo = "Desafío 1: Seres vivos",
                descripcion = "Reconocer seres vivos.",
                instrucciones = "Observa tu entorno e identifica " +
                        "plantas y animales.",
                completado = true
            ),
            DesafioDemoEstudiante(
                titulo = "Desafío 2: Cuidemos la naturaleza",
                descripcion = "Aprender a cuidar nuestro entorno.",
                instrucciones = "Piensa en acciones que ayudan " +
                        "a cuidar las plantas y los animales.",
                completado = false
            )
        )

        4 -> listOf(
            DesafioDemoEstudiante(
                titulo = "Desafío 1: Mi comunidad",
                descripcion = "Reconocer lugares de la comunidad.",
                instrucciones = "Identifica lugares como " +
                        "la escuela, el hospital y la plaza.",
                completado = true
            ),
            DesafioDemoEstudiante(
                titulo = "Desafío 2: Buena convivencia",
                descripcion = "Reconocer normas de convivencia.",
                instrucciones = "Piensa en cómo podemos " +
                        "respetar y ayudar a otras personas.",
                completado = false
            )
        )

        else -> emptyList()
    }

    // Permite abrir el detalle sin salir de la pantalla
    var desafioSeleccionado by remember(asignaturaId) {
        mutableStateOf<DesafioDemoEstudiante?>(null)
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(desafioSeleccionado) {
        scrollState.scrollTo(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if (desafioSeleccionado == null) {

            // LISTADO DE DESAFÍOS

            OutlinedButton(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← Volver a la asignatura")
            }

            Text(
                text = "Mis desafíos",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = nombreAsignatura,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Unidad 1 · Desafíos de aprendizaje",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Estados de demostración: " +
                        "no representan avances reales.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider()

            Text(
                text = "Desafíos disponibles",
                style = MaterialTheme.typography.titleMedium
            )

            desafios.forEach { desafio ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = desafio.titulo,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = desafio.descripcion,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = if (desafio.completado) {
                                "✓ Completado (ejemplo)"
                            } else {
                                "◷ Pendiente (ejemplo)"
                            },
                            color = if (desafio.completado) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )

                        Button(
                            onClick = {
                                desafioSeleccionado = desafio
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Ver desafío")
                        }
                    }
                }
            }

        } else {

            // DETALLE DEL DESAFÍO

            val desafio = desafioSeleccionado!!

            OutlinedButton(
                onClick = {
                    desafioSeleccionado = null
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← Volver a Mis desafíos")
            }

            Text(
                text = "Detalle del desafío",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = nombreAsignatura,
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider()

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = desafio.titulo,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Objetivo",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(desafio.descripcion)

                    Text(
                        text = "Instrucciones",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(desafio.instrucciones)

                    Text(
                        text = if (desafio.completado) {
                            "Estado: Completado (ejemplo)"
                        } else {
                            "Estado: Pendiente (ejemplo)"
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (desafio.completado) {

                Text(
                    text = "¡Muy bien! Este desafío aparece " +
                            "como completado en la demostración.",
                    style = MaterialTheme.typography.bodyLarge
                )

            } else {

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
                    Text("Realizar desafío")
                }
            }

            Text(
                text = "Esta pantalla es demostrativa. " +
                        "El progreso no se guarda.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
