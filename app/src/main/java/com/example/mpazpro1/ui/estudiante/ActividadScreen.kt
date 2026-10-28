package com.example.mpazpro1.ui.estudiante

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Preguntas de demostración para cada asignatura
private data class PreguntaEstudiante(
    val titulo: String,
    val pregunta: String,
    val alternativas: List<String>,
    val respuestaCorrecta: String,
    val explicacion: String
)

@Composable
fun ActividadScreen(
    onVolver: () -> Unit,
    asignaturaId: Int = 1
) {
    val actividad = when (asignaturaId) {

        1 -> PreguntaEstudiante(
            titulo = "Actividad de Lenguaje",
            pregunta = "¿Cuál de estas letras es una vocal?",
            alternativas = listOf("M", "A", "P"),
            respuestaCorrecta = "A",
            explicacion = "La letra A es una vocal."
        )

        2 -> PreguntaEstudiante(
            titulo = "Actividad de Matemática",
            pregunta = "¿Cuánto es 2 + 3?",
            alternativas = listOf("4", "5", "6"),
            respuestaCorrecta = "5",
            explicacion = "Dos más tres es igual a cinco."
        )

        3 -> PreguntaEstudiante(
            titulo = "Actividad de Ciencias Naturales",
            pregunta = "¿Cuál de estos es un ser vivo?",
            alternativas = listOf(
                "Piedra",
                "Árbol",
                "Mesa"
            ),
            respuestaCorrecta = "Árbol",
            explicacion = "El árbol es un ser vivo porque " +
                    "crece y necesita agua y luz."
        )

        4 -> PreguntaEstudiante(
            titulo = "Actividad de Historia y Geografía",
            pregunta = "¿A qué lugar vamos para estudiar " +
                    "y aprender con nuestros profesores?",
            alternativas = listOf(
                "Supermercado",
                "Escuela",
                "Hospital"
            ),
            respuestaCorrecta = "Escuela",
            explicacion = "En la escuela aprendemos " +
                    "y convivimos con otras personas."
        )

        else -> PreguntaEstudiante(
            titulo = "Actividad educativa",
            pregunta = "¿Cuál de estas letras es una vocal?",
            alternativas = listOf("M", "A", "P"),
            respuestaCorrecta = "A",
            explicacion = "La letra A es una vocal."
        )
    }

    var respuesta by remember(asignaturaId) {
        mutableStateOf("")
    }

    var resultado by remember(asignaturaId) {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = actividad.titulo,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Lee la pregunta y selecciona " +
                    "la respuesta correcta.",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        Text(
            text = actividad.pregunta,
            style = MaterialTheme.typography.titleLarge
        )

        actividad.alternativas.forEach { alternativa ->

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                RadioButton(
                    selected = respuesta == alternativa,
                    onClick = {
                        respuesta = alternativa
                        resultado = null
                    }
                )

                Text(
                    text = alternativa,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Button(
            onClick = {
                resultado = if (
                    respuesta == actividad.respuestaCorrecta
                ) {
                    "¡Muy bien! ${actividad.explicacion}"
                } else {
                    "Inténtalo nuevamente. " +
                            "Lee la pregunta y vuelve a elegir."
                }
            },
            enabled = respuesta.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comprobar respuesta")
        }

        resultado?.let { mensaje ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = mensaje,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Text(
            text = "Actividad educativa de demostración.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver a la asignatura")
        }
    }
}
