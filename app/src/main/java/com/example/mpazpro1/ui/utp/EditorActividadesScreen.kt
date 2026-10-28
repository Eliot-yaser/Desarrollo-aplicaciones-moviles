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

@Composable
fun EditorActividadesScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    // Datos del formulario
    var tipo by remember {
        mutableStateOf("Actividad educativa")
    }

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

    var pregunta by remember { mutableStateOf("") }

    var alternativaA by remember { mutableStateOf("") }
    var alternativaB by remember { mutableStateOf("") }
    var alternativaC by remember { mutableStateOf("") }

    var respuestaCorrecta by remember {
        mutableStateOf("Alternativa A")
    }

    var explicacion by remember { mutableStateOf("") }
    var recurso by remember { mutableStateOf("") }

    var dificultad by remember {
        mutableStateOf("Fácil")
    }

    var orden by remember { mutableStateOf("") }

    var estado by remember {
        mutableStateOf("Borrador")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // Regresar a Revision de contenidos
        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver a Revisión de contenidos")
        }

        Text(
            text = "Editor de actividades y desafíos",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Crear actividades educativas de 1° Básico.",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        Text(
            text = "Información general",
            style = MaterialTheme.typography.titleMedium
        )

        // Tipo de material
        SelectorActividadUtp(
            titulo = "Tipo de material",
            opciones = listOf(
                "Actividad educativa",
                "Desafío educativo"
            ),
            seleccionado = tipo,
            onSeleccionar = { tipo = it }
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            placeholder = {
                Text("Ejemplo: Reconocimiento de vocales")
            },
            modifier = Modifier.fillMaxWidth()
        )

        SelectorActividadUtp(
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

        SelectorActividadUtp(
            titulo = "Unidad",
            opciones = listOf(
                "Unidad 1",
                "Unidad 2",
                "Unidad 3"
            ),
            seleccionado = unidad,
            onSeleccionar = { unidad = it }
        )

        SelectorActividadUtp(
            titulo = "Objetivo de aprendizaje",
            opciones = listOf(
                "OA de ejemplo 1",
                "OA de ejemplo 2",
                "OA de ejemplo 3"
            ),
            seleccionado = objetivo,
            onSeleccionar = { objetivo = it }
        )

        HorizontalDivider()

        Text(
            text = "Pregunta y alternativas",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = pregunta,
            onValueChange = { pregunta = it },
            label = { Text("Pregunta") },
            placeholder = {
                Text("¿Cuál de estas letras es una vocal?")
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

        SelectorActividadUtp(
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
            value = explicacion,
            onValueChange = { explicacion = it },
            label = { Text("Explicación de la respuesta") },
            placeholder = {
                Text("Explica por qué es correcta")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        HorizontalDivider()

        Text(
            text = "Configuración de la actividad",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = recurso,
            onValueChange = { recurso = it },
            label = { Text("Recurso asociado") },
            placeholder = {
                Text("Imagen, audio o guía de ejemplo")
            },
            modifier = Modifier.fillMaxWidth()
        )

        SelectorActividadUtp(
            titulo = "Dificultad",
            opciones = listOf(
                "Fácil",
                "Media",
                "Difícil"
            ),
            seleccionado = dificultad,
            onSeleccionar = { dificultad = it }
        )

        OutlinedTextField(
            value = orden,
            onValueChange = { orden = it },
            label = { Text("Orden de presentación") },
            placeholder = { Text("Ejemplo: 1") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        SelectorActividadUtp(
            titulo = "Estado",
            opciones = listOf(
                "Borrador",
                "En revisión",
                "Aprobado"
            ),
            seleccionado = estado,
            onSeleccionar = { estado = it }
        )

        HorizontalDivider()

        Text(
            text = "Este formulario es demostrativo. " +
                    "Los datos no se guardarán.",
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
            Text(
                if (tipo == "Desafío educativo") {
                    "Guardar desafío"
                } else {
                    "Guardar actividad"
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// Selector reutilizable para los formularios
@Composable
private fun SelectorActividadUtp(
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
