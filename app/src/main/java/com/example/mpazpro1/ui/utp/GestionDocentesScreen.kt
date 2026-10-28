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

// Docentes ficticios para la demostración
private data class DocenteDemoUtp(
    val nombre: String,
    val correo: String,
    val curso: String,
    val asignaturas: String,
    val estado: String
)

@Composable
fun GestionDocentesScreen(
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    val docentes = remember {
        listOf(
            DocenteDemoUtp(
                nombre = "María González",
                correo = "maria@example.com",
                curso = "1.º Básico A",
                asignaturas = "Lenguaje y Matemática",
                estado = "Activo"
            ),
            DocenteDemoUtp(
                nombre = "Carlos Pérez",
                correo = "carlos@example.com",
                curso = "1.º Básico B",
                asignaturas = "Ciencias Naturales e Historia",
                estado = "Activo"
            )
        )
    }

    // Control de la pantalla
    var mostrarFormulario by remember {
        mutableStateOf(false)
    }

    var docenteSeleccionado by remember {
        mutableStateOf<DocenteDemoUtp?>(null)
    }

    // Campos del formulario
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    var curso by remember {
        mutableStateOf("Sin asignar")
    }

    var asignaturas by remember {
        mutableStateOf("")
    }

    var estado by remember {
        mutableStateOf("Activo")
    }

    var observaciones by remember {
        mutableStateOf("")
    }

    // Mostrar mensaje de futura actualización
    fun mostrarMensaje() {
        Toast.makeText(
            context,
            "Para una futura actualización",
            Toast.LENGTH_SHORT
        ).show()
    }

    // Abrir formulario para agregar
    fun agregarDocente() {
        docenteSeleccionado = null
        nombre = ""
        correo = ""
        curso = "Sin asignar"
        asignaturas = ""
        estado = "Activo"
        observaciones = ""
        mostrarFormulario = true
    }

    // Abrir formulario para editar
    fun editarDocente(docente: DocenteDemoUtp) {
        docenteSeleccionado = docente
        nombre = docente.nombre
        correo = docente.correo
        curso = docente.curso
        asignaturas = docente.asignaturas
        estado = docente.estado
        observaciones = ""
        mostrarFormulario = true
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(mostrarFormulario) {
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

        if (!mostrarFormulario) {

            // PANTALLA PRINCIPAL

            OutlinedButton(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← Volver al panel UTP")
            }

            Text(
                text = "Gestión de docentes",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Administración de docentes de 1.º Básico.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Datos ficticios para demostración.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider()

            Text(
                text = "Docentes registrados",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Total de docentes de ejemplo: ${docentes.size}"
            )

            // LISTADO DE DOCENTES

            docentes.forEach { docente ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Text(
                            text = docente.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Correo: ${docente.correo}"
                        )

                        Text(
                            text = "Curso: ${docente.curso}"
                        )

                        Text(
                            text = "Asignaturas: ${docente.asignaturas}"
                        )

                        Text(
                            text = "Estado: ${docente.estado}",
                            color = MaterialTheme.colorScheme.primary
                        )

                        Button(
                            onClick = {
                                editarDocente(docente)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Editar docente")
                        }

                        OutlinedButton(
                            onClick = {
                                mostrarMensaje()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cambiar estado")
                        }
                    }
                }
            }

            HorizontalDivider()

            Button(
                onClick = {
                    agregarDocente()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("Agregar docente")
            }

            Text(
                text = "Las opciones de administración son " +
                        "demostrativas y no modifican usuarios reales.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        } else {

            // FORMULARIO PARA AGREGAR O EDITAR

            OutlinedButton(
                onClick = {
                    mostrarFormulario = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← Volver a Gestión de docentes")
            }

            Text(
                text = if (docenteSeleccionado == null) {
                    "Agregar docente"
                } else {
                    "Editar docente"
                },
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Formulario de administración de docentes.",
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider()

            Text(
                text = "Datos personales",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                },
                label = {
                    Text("Nombre completo")
                },
                placeholder = {
                    Text("Nombre del docente")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                },
                label = {
                    Text("Correo electrónico")
                },
                placeholder = {
                    Text("docente@ejemplo.cl")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            HorizontalDivider()

            Text(
                text = "Asignación académica",
                style = MaterialTheme.typography.titleMedium
            )

            SelectorDocentesUtp(
                titulo = "Curso asignado",
                opciones = listOf(
                    "Sin asignar",
                    "1.º Básico A",
                    "1.º Básico B",
                    "Ambos cursos"
                ),
                seleccionado = curso,
                onSeleccionar = {
                    curso = it
                }
            )

            OutlinedTextField(
                value = asignaturas,
                onValueChange = {
                    asignaturas = it
                },
                label = {
                    Text("Asignaturas asignadas")
                },
                placeholder = {
                    Text("Ejemplo: Lenguaje, Matemática")
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            HorizontalDivider()

            Text(
                text = "Administración",
                style = MaterialTheme.typography.titleMedium
            )

            SelectorDocentesUtp(
                titulo = "Estado del docente",
                opciones = listOf(
                    "Activo",
                    "Inactivo"
                ),
                seleccionado = estado,
                onSeleccionar = {
                    estado = it
                }
            )

            OutlinedTextField(
                value = observaciones,
                onValueChange = {
                    observaciones = it
                },
                label = {
                    Text("Observaciones administrativas")
                },
                placeholder = {
                    Text("Escribir observaciones...")
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            HorizontalDivider()

            Text(
                text = "Este formulario es demostrativo. " +
                        "No se guardarán cambios en los docentes.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = {
                    mostrarMensaje()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    if (docenteSeleccionado == null) {
                        "Guardar docente"
                    } else {
                        "Guardar cambios"
                    }
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}

// Selector reutilizable para gestión de docentes
@Composable
private fun SelectorDocentesUtp(
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
