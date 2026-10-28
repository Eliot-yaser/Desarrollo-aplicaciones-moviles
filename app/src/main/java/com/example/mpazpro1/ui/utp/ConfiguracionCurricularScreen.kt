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
fun ConfiguracionCurricularScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    // Asignaturas de Primero Básico
    val asignaturas = listOf(
        "Lenguaje y Comunicación",
        "Matemática",
        "Ciencias Naturales",
        "Historia, Geografía y Cs. Sociales"
    )

    var asignaturaSeleccionada by remember {
        mutableStateOf<String?>(null)
    }

    var formulario by remember {
        mutableStateOf<String?>(null)
    }

    // Campos de los formularios
    var nombreUnidad by remember {
        mutableStateOf("")
    }

    var descripcionUnidad by remember {
        mutableStateOf("")
    }

    var codigoOA by remember {
        mutableStateOf("")
    }

    var descripcionOA by remember {
        mutableStateOf("")
    }

    var eje by remember {
        mutableStateOf("")
    }

    var referencia by remember {
        mutableStateOf("")
    }

    var responsable by remember {
        mutableStateOf("")
    }

    var versionFecha by remember {
        mutableStateOf("")
    }

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

        // Navegación hacia atrás
        OutlinedButton(
            onClick = {
                when {
                    formulario != null -> {
                        formulario = null
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
            text = formulario
                ?: asignaturaSeleccionada
                ?: "Configuración curricular",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Administración académica · 1° Básico",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        // Vista principal: asignaturas
        if (asignaturaSeleccionada == null) {

            Text(
                text = "Asignaturas disponibles",
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

                        Text("Nivel: 1° Básico")

                        Text(
                            text = "Ver unidades y objetivos →",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Vista de unidades y objetivos
        else if (formulario == null) {

            Text(
                text = "Unidades y objetivos",
                style = MaterialTheme.typography.titleMedium
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Unidad 1 - Introducción",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text("Nivel: 1° Básico")

                    Text("OA de demostración 1")

                    Text(
                        "Objetivo ilustrativo asociado a la asignatura."
                    )

                    Text("Estado: Borrador")

                    Text(
                        text = "Datos ficticios para esta evaluación.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Button(
                onClick = {
                    nombreUnidad = ""
                    descripcionUnidad = ""
                    formulario = "Crear unidad"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear unidad")
            }

            OutlinedButton(
                onClick = {
                    nombreUnidad = "Unidad 1 - Introducción"
                    descripcionUnidad = "Descripción de ejemplo"
                    formulario = "Editar unidad"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar unidad")
            }

            Button(
                onClick = {
                    codigoOA = ""
                    descripcionOA = ""
                    formulario = "Registrar objetivo"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar objetivo de aprendizaje")
            }

            OutlinedButton(
                onClick = {
                    codigoOA = "OA de demostración 1"
                    descripcionOA = "Objetivo ilustrativo"
                    formulario = "Editar objetivo"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar objetivo de aprendizaje")
            }
        }

        // Formularios visuales
        else {

            Text(
                text = "Asignatura: $asignaturaSeleccionada",
                style = MaterialTheme.typography.bodyMedium
            )

            Text("Nivel: 1° Básico")

            // Formulario para unidades
            if (formulario == "Crear unidad" ||
                formulario == "Editar unidad"
            ) {

                OutlinedTextField(
                    value = nombreUnidad,
                    onValueChange = { nombreUnidad = it },
                    label = { Text("Nombre de la unidad") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcionUnidad,
                    onValueChange = { descripcionUnidad = it },
                    label = { Text("Descripción de la unidad") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }

            // Formulario para objetivos de aprendizaje
            else {

                OutlinedTextField(
                    value = codigoOA,
                    onValueChange = { codigoOA = it },
                    label = { Text("Código del OA") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcionOA,
                    onValueChange = { descripcionOA = it },
                    label = { Text("Descripción del OA") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                OutlinedTextField(
                    value = eje,
                    onValueChange = { eje = it },
                    label = { Text("Eje de aprendizaje") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = referencia,
                    onValueChange = { referencia = it },
                    label = { Text("Referencia curricular") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            OutlinedTextField(
                value = responsable,
                onValueChange = { responsable = it },
                label = { Text("Responsable UTP") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = versionFecha,
                onValueChange = { versionFecha = it },
                label = { Text("Versión o fecha") },
                modifier = Modifier.fillMaxWidth()
            )

            // Estado curricular
            var expandido by remember {
                mutableStateOf(false)
            }

            Text(
                text = "Estado",
                style = MaterialTheme.typography.labelLarge
            )

            Box {
                OutlinedButton(
                    onClick = { expandido = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(estado)
                }

                DropdownMenu(
                    expanded = expandido,
                    onDismissRequest = {
                        expandido = false
                    }
                ) {
                    listOf(
                        "Borrador",
                        "En revisión",
                        "Aprobado"
                    ).forEach { opcion ->

                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                estado = opcion
                                expandido = false
                            }
                        )
                    }
                }
            }

            Text(
                text = "Formulario demostrativo. Los datos no se guardarán.",
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
                Text("Guardar")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
