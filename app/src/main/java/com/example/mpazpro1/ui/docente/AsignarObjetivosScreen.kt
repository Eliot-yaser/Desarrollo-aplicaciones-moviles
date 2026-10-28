
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
fun AsignarObjetivosScreen(
    onVolver: () -> Unit,
    onConfigurarPruebaFinal: () -> Unit
) {
    val context = LocalContext.current

    var curso by remember { mutableStateOf("1° Básico A") }
    var estudiante by remember { mutableStateOf("") }
    var asignatura by remember { mutableStateOf("Lenguaje y Comunicación") }
    var unidad by remember { mutableStateOf("Unidad 1") }
    var objetivo by remember { mutableStateOf("OA de ejemplo 1") }
    var actividad by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver al panel docente")
        }

        Text(
            text = "Asignar objetivos de aprendizaje",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Configura los objetivos y actividades del curso.",
            style = MaterialTheme.typography.bodyMedium
        )

        SelectorFormulario(
            titulo = "Curso",
            opciones = listOf("1° Básico A", "1° Básico B"),
            seleccionado = curso,
            onSeleccionar = { curso = it }
        )

        OutlinedTextField(
            value = estudiante,
            onValueChange = { estudiante = it },
            label = { Text("Estudiante (opcional)") },
            placeholder = { Text("Dejar vacío para todo el curso") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        SelectorFormulario(
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

        SelectorFormulario(
            titulo = "Unidad",
            opciones = listOf("Unidad 1", "Unidad 2", "Unidad 3"),
            seleccionado = unidad,
            onSeleccionar = { unidad = it }
        )

        SelectorFormulario(
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
            value = actividad,
            onValueChange = { actividad = it },
            label = { Text("Contenido o actividad") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha de disponibilidad") },
            placeholder = { Text("DD/MM/AAAA") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedButton(
            onClick = onConfigurarPruebaFinal,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Configurar prueba final")
        }

        Spacer(modifier = Modifier.height(12.dp))

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
            Text("Asignar objetivo")
        }
    }
}

@Composable
private fun SelectorFormulario(
    titulo: String,
    opciones: List<String>,
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Column {
        Text(
            text = titulo,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box {
            OutlinedButton(
                onClick = { expandido = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(seleccionado)
            }

            DropdownMenu(
                expanded = expandido,
                onDismissRequest = { expandido = false }
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
