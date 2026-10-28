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
fun EditorContenidosScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    // Datos del contenido educativo
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

    var explicacion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var audio by remember { mutableStateOf("") }
    var ejemploGuiado by remember { mutableStateOf("") }
    var instrucciones by remember { mutableStateOf("") }
    var orden by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var origen by remember { mutableStateOf("") }
    var autorizacion by remember {
        mutableStateOf("Material de demostración")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // Regresar a la pantalla anterior
        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver a Revisión de contenidos")
        }

        Text(
            text = "Editor de contenidos",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Crear o editar material educativo de 1° Básico.",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        Text(
            text = "Información del contenido",
            style = MaterialTheme.typography.titleMedium
        )

        CampoEditorContenido(
            titulo = "Título del contenido",
            valor = titulo,
            onCambio = { titulo = it }
        )

        SelectorEditorContenido(
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

        SelectorEditorContenido(
            titulo = "Unidad",
            opciones = listOf(
                "Unidad 1",
                "Unidad 2",
                "Unidad 3"
            ),
            seleccionado = unidad,
            onSeleccionar = { unidad = it }
        )

        SelectorEditorContenido(
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
            text = "Material educativo",
            style = MaterialTheme.typography.titleMedium
        )

        CampoEditorContenido(
            titulo = "Explicación del contenido",
            valor = explicacion,
            onCambio = { explicacion = it },
            variasLineas = true
        )

        CampoEditorContenido(
            titulo = "Imagen de apoyo (nombre o referencia)",
            valor = imagen,
            onCambio = { imagen = it }
        )

        CampoEditorContenido(
            titulo = "Audio educativo (nombre o referencia)",
            valor = audio,
            onCambio = { audio = it }
        )

        CampoEditorContenido(
            titulo = "Ejemplo guiado",
            valor = ejemploGuiado,
            onCambio = { ejemploGuiado = it },
            variasLineas = true
        )

        CampoEditorContenido(
            titulo = "Instrucciones para el estudiante",
            valor = instrucciones,
            onCambio = { instrucciones = it },
            variasLineas = true
        )

        HorizontalDivider()

        Text(
            text = "Información administrativa",
            style = MaterialTheme.typography.titleMedium
        )

        CampoEditorContenido(
            titulo = "Orden del contenido",
            valor = orden,
            onCambio = { orden = it }
        )

        CampoEditorContenido(
            titulo = "Autor del material",
            valor = autor,
            onCambio = { autor = it }
        )

        CampoEditorContenido(
            titulo = "Origen o fuente del material",
            valor = origen,
            onCambio = { origen = it }
        )

        SelectorEditorContenido(
            titulo = "Autorización del material",
            opciones = listOf(
                "Material de demostración",
                "Material propio",
                "Material autorizado"
            ),
            seleccionado = autorizacion,
            onSeleccionar = { autorizacion = it }
        )

        Text(
            text = "Formulario demostrativo. Los datos no se guardarán.",
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
            Text("Guardar contenido")
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

// Campo de texto reutilizable
@Composable
private fun CampoEditorContenido(
    titulo: String,
    valor: String,
    onCambio: (String) -> Unit,
    variasLineas: Boolean = false
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(titulo) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = !variasLineas,
        minLines = if (variasLineas) 3 else 1
    )
}

// Selector reutilizable
@Composable
private fun SelectorEditorContenido(
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
