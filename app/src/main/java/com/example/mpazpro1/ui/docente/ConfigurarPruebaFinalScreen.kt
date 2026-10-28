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
fun ConfigurarPruebaFinalScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    // Datos de ejemplo del formulario
    var curso by remember {
        mutableStateOf("1° Básico A")
    }

    var asignatura by remember {
        mutableStateOf("Lenguaje y Comunicación")
    }

    var unidad by remember {
        mutableStateOf("Unidad 1")
    }

    var objetivo by remember {
        mutableStateOf("OA de ejemplo 1")
    }

    var prueba by remember {
        mutableStateOf("Prueba final de unidad")
    }

    var fecha by remember {
        mutableStateOf("")
    }

    var intentos by remember {
        mutableStateOf("2 intentos")
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
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver al panel docente")
        }

        Text(
            text = "Configurar prueba final",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Configura una evaluación para los estudiantes de 1° Básico.",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        Text(
            text = "Datos de la evaluación",
            style = MaterialTheme.typography.titleMedium
        )

        // Seleccionar curso
        SelectorPruebaFinal(
            titulo = "Curso",
            opciones = listOf(
                "1° Básico A",
                "1° Básico B"
            ),
            seleccionado = curso,
            onSeleccionar = { curso = it }
        )

        // Seleccionar asignatura
        SelectorPruebaFinal(
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

        // Seleccionar unidad
        SelectorPruebaFinal(
            titulo = "Unidad",
            opciones = listOf(
                "Unidad 1",
                "Unidad 2",
                "Unidad 3"
            ),
            seleccionado = unidad,
            onSeleccionar = { unidad = it }
        )

        // Seleccionar objetivo
        SelectorPruebaFinal(
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
            text = "Configuración de la prueba",
            style = MaterialTheme.typography.titleMedium
        )

        // Seleccionar prueba
        SelectorPruebaFinal(
            titulo = "Prueba",
            opciones = listOf(
                "Prueba final de unidad",
                "Evaluación de repaso"
            ),
            seleccionado = prueba,
            onSeleccionar = { prueba = it }
        )

        // Fecha de habilitación
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = {
                Text("Fecha de habilitación")
            },
            placeholder = {
                Text("DD/MM/AAAA")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Número máximo de intentos
        SelectorPruebaFinal(
            titulo = "Número máximo de intentos",
            opciones = listOf(
                "1 intento",
                "2 intentos",
                "3 intentos"
            ),
            seleccionado = intentos,
            onSeleccionar = { intentos = it }
        )

        HorizontalDivider()

        Text(
            text = "Esta configuración es demostrativa y no se guardará.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Botón de confirmación
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
            Text("Guardar configuración")
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// Componente reutilizable para los selectores
@Composable
private fun SelectorPruebaFinal(
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
}
