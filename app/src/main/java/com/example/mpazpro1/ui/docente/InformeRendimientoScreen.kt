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

@Composable
fun InformeRendimientoScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    var mostrarDetalle by remember {
        mutableStateOf(false)
    }

    var observacion by remember {
        mutableStateOf("")
    }

    val asignaturas = listOf(
        "Lenguaje y Comunicación" to 72,
        "Matemática" to 80,
        "Ciencias Naturales" to 85,
        "Historia y Ciencias Sociales" to 75
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedButton(
            onClick = {
                if (mostrarDetalle) {
                    mostrarDetalle = false
                } else {
                    onVolver()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (mostrarDetalle) {
                    "← Volver al resumen"
                } else {
                    "← Volver al panel docente"
                }
            )
        }

        Text(
            text = if (mostrarDetalle) {
                "Informe pedagógico"
            } else {
                "Rendimiento del curso"
            },
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "1° Básico A · Datos de ejemplo",
            style = MaterialTheme.typography.bodyMedium
        )

        if (!mostrarDetalle) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IndicadorInforme(
                    valor = "78%",
                    titulo = "Avance",
                    modifier = Modifier.weight(1f)
                )

                IndicadorInforme(
                    valor = "18",
                    titulo = "Estudiantes",
                    modifier = Modifier.weight(1f)
                )

                IndicadorInforme(
                    valor = "4",
                    titulo = "Por reforzar",
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = "Rendimiento por asignatura",
                style = MaterialTheme.typography.titleMedium
            )

            asignaturas.forEach { (nombre, porcentaje) ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = nombre,
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text("Avance: $porcentaje%")

                        LinearProgressIndicator(
                            progress = { porcentaje / 100f },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Text(
                text = "Fortalezas del curso",
                style = MaterialTheme.typography.titleMedium
            )

            Text("✓ Comprensión lectora")
            Text("✓ Reconocimiento de números")

            Text(
                text = "Áreas por reforzar",
                style = MaterialTheme.typography.titleMedium
            )

            Text("• Escritura de palabras")
            Text("• Resolución de problemas")

            Button(
                onClick = {
                    mostrarDetalle = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("Ver informe detallado")
            }

        } else {

            Text(
                text = "Información del estudiante",
                style = MaterialTheme.typography.titleMedium
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Estudiante: Estudiante 01")
                    Text("Curso: 1° Básico A")
                    Text("Asignatura: Lenguaje y Comunicación")
                    Text("Unidad: Unidad 1")
                    Text("Docente: Docente de ejemplo")
                    Text("Fecha: 27/10/2026")
                    Text("Estado: Pendiente de validación")
                }
            }

            Text(
                text = "Resultados por objetivo",
                style = MaterialTheme.typography.titleMedium
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("OA de ejemplo 1")
                    Text("Reconocimiento de vocales")
                    Text("Respuestas correctas: 8")
                    Text("Respuestas incorrectas: 2")
                    Text("Rendimiento: 80%")

                    LinearProgressIndicator(
                        progress = { 0.8f },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Text(
                text = "Fortalezas",
                style = MaterialTheme.typography.titleMedium
            )

            Text("✓ Reconoce vocales en palabras.")
            Text("✓ Identifica sonidos iniciales.")

            Text(
                text = "Aspectos por reforzar",
                style = MaterialTheme.typography.titleMedium
            )

            Text("• Escritura de vocales.")
            Text("• Asociación de sonidos e imágenes.")

            OutlinedTextField(
                value = observacion,
                onValueChange = { observacion = it },
                label = {
                    Text("Observación pedagógica")
                },
                placeholder = {
                    Text("Escribir observación de ejemplo")
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Para una futura actualización",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Validar informe")
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
                Text("Descargar informe")
            }
        }
    }
}

@Composable
private fun IndicadorInforme(
    valor: String,
    titulo: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = valor,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = titulo,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
