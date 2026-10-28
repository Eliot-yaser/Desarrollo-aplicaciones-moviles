package com.example.mpazpro1.ui.utp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EditorPruebaFinalScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    // Información general de la evaluación
    var titulo by remember { mutableStateOf("") }

    var asignatura by remember {
        mutableStateOf("Lenguaje y Comunicación")
    }

    var unidad by remember {
        mutableStateOf("Unidad 1")
    }

    var objetivo by remember {
        mutableStateOf("OA de ejemplo 1")
    }

    var instrucciones by remember {
        mutableStateOf("")
    }

    // Configuración de la prueba
    var cantidadPreguntas by remember {
        mutableStateOf("5")
    }

    var intentosMaximos by remember {
        mutableStateOf("2")
    }

    var fechaDisponibilidad by remember {
        mutableStateOf("")
    }

    var estado by remember {
        mutableStateOf("Borrador")
    }

    // Pregunta de ejemplo
    var pregunta by remember {
        mutableStateOf("")
    }

    var alternativaA by remember {
        mutableStateOf("")
    }

    var alternativaB by remember {
        mutableStateOf("")
    }

    var alternativaC by remember {
        mutableStateOf("")
    }

    var respuestaCorrecta by remember {
        mutableStateOf("Alternativa A")
    }

    var retroalimentacion by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver a Revisión de contenidos")
        }

        Text(
            text = "Editor de prueba final",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Configuración demostrativa de evaluaciones " +
                    "para estudiantes de 1° Básico.",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        // SECCIÓN 1
        Text(
            text = "Información general",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título de la prueba") },
            placeholder = {
                Text("Ejemplo: Evaluación final - Unidad 1")
            },
            modifier = Modifier.fillMaxWidth()
        )

        SelectorPruebaFinalUtp(
            titulo = "Asignatura",
            opciones = listOf(
                "Lenguaje y Comunicación",
                "Matemática",
                "Ciencias Naturales",
                "Historia, Geografía y Cs. Sociales"
            ),
            seleccionado = asignatura,
            onSeleccionar = { asignatura = it }
        )

        SelectorPruebaFinalUtp(
            titulo = "Unidad",
            opciones = listOf(
                "Unidad 1",
                "Unidad 2",
                "Unidad 3"
            ),
            seleccionado = unidad,
            onSeleccionar = { unidad = it }
        )

        SelectorPruebaFinalUtp(
            titulo = "Objetivo de aprendizaje",
            opciones = listOf(
                "OA de ejemplo 1",
                "OA de ejemplo 2",
                "OA de ejemplo 3"
            ),
            seleccionado = objetivo,
            onSeleccionar = { objetivo = it }
        )

        OutlinedTextField(
            value = instrucciones,
            onValueChange = { instrucciones = it },
            label = { Text("Instrucciones de la prueba") },
            placeholder = {
                Text("Lee cada pregunta y selecciona una respuesta.")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        HorizontalDivider()

        // SECCIÓN 2
        Text(
            text = "Configuración de la evaluación",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = cantidadPreguntas,
            onValueChange = { cantidadPreguntas = it },
            label = { Text("Cantidad de preguntas") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = intentosMaximos,
            onValueChange = { intentosMaximos = it },
            label = { Text("Intentos máximos") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = fechaDisponibilidad,
            onValueChange = { fechaDisponibilidad = it },
            label = { Text("Fecha de disponibilidad") },
            placeholder = { Text("DD/MM/AAAA") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        SelectorPruebaFinalUtp(
            titulo = "Estado de la prueba",
            opciones = listOf(
                "Borrador",
                "En revisión",
                "Aprobada"
            ),
            seleccionado = estado,
            onSeleccionar = { estado = it }
        )

        HorizontalDivider()

        // SECCIÓN 3
        Text(
            text = "Editor de pregunta",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Pregunta de demostración. " +
                    "En una futura versión se podrán añadir " +
                    "y administrar múltiples preguntas.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedTextField(
            value = pregunta,
            onValueChange = { pregunta = it },
            label = { Text("Enunciado de la pregunta") },
            placeholder = {
                Text("Ejemplo: ¿Cuánto es 2 + 3?")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2
        )

        OutlinedTextField(
            value = alternativaA,
            onValueChange = { alternativaA = it },
            label = { Text("Alternativa A") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alternativaB,
            onValueChange = { alternativaB = it },
            label = { Text("Alternativa B") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alternativaC,
            onValueChange = { alternativaC = it },
            label = { Text("Alternativa C") },
            modifier = Modifier.fillMaxWidth()
        )

        SelectorPruebaFinalUtp(
            titulo = "Respuesta correcta",
            opciones = listOf(
                "Alternativa A",
                "Alternativa B",
                "Alternativa C"
            ),
            seleccionado = respuestaCorrecta,
            onSeleccionar = { respuestaCorrecta = it }
        )

        OutlinedTextField(
            value = retroalimentacion,
            onValueChange = { retroalimentacion = it },
            label = { Text("Retroalimentación de la respuesta") },
            placeholder = {
                Text("Explica por qué la respuesta es correcta.")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        HorizontalDivider()

        Text(
            text = "Este formulario es demostrativo. " +
                    "No se guardarán datos ni se publicará la prueba.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            Text("Guardar prueba final")
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// Selector reutilizable para esta pantalla
@Composable
private fun SelectorPruebaFinalUtp(
    titulo: String,
    opciones: List<String>,
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {
    var expandido by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.labelLarge
        )

        Box {
            OutlinedButton(
                onClick = { expandido = true },
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
                        text = { Text(opcion) },
                        onClick = {
                            onSeleccionar(opcion)
                            expandido = false
                        }
                    )
                }
            }
        }
    }
}
